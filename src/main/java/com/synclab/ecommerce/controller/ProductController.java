package com.synclab.ecommerce.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.synclab.ecommerce.model.Category;
import com.synclab.ecommerce.model.Product;
import com.synclab.ecommerce.service.category.CategoryServiceImplementation;
import com.synclab.ecommerce.service.product.ProductServiceImplementation;
import com.synclab.ecommerce.utility.exception.RecordNotFoundException;
import com.synclab.ecommerce.utility.pages.PageUtils;
import com.synclab.ecommerce.utility.response.CustomResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

	// fields

	@Autowired
	private ProductServiceImplementation productServiceImplementation;

	@Autowired
	private CategoryServiceImplementation categoryServiceImplementation;

	// post

	@PostMapping(value = "/insert", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Product> insert(@RequestBody Product requestBody) {

		if (requestBody == null) {
			// body is empty, return error message
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		Product newProduct = requestBody;
		List<Category> categories = newProduct.getCategories();
		List<Category> newCategories = new ArrayList<>();
		// List<Image> images = newProduct.getImage();

		// other category if product isn't of any known category
		Category otherCategory = categoryServiceImplementation.findByName("Other");

		if (categories != null) {
			for (int i = 0; i < categories.size(); i++) {
				String categoryName = categories.get(i).getName();
				Category existingCategory = categoryServiceImplementation.findByName(categoryName);
				if (existingCategory != null) {
					newCategories.add(existingCategory);
				} else if (!newCategories.contains(otherCategory)) {
					newCategories.add(otherCategory);
				}
			}
			newProduct.setCategories(newCategories);
		}

		// add to database
		productServiceImplementation.insert(newProduct);

		return ResponseEntity.ok(newProduct);

	}

	// get

	@GetMapping(value = "/get/{id}", produces = "application/json")
	public ResponseEntity<Product> findById(@PathVariable(value = "id") Long id) {

		Product newProduct = productServiceImplementation.findById(id);

		return CustomResponse.getFindResponse(newProduct, "product not found",
				"product with id: " + id + " could not be found.");

	}

	@GetMapping(value = "/getFromQuery", produces = "application/json")
	public ResponseEntity<Page<Product>> findById(@RequestParam String query,
			@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {

		List<Product> list = productServiceImplementation.rsqlQuery(query);
		return PageUtils.listToPageResponseEntity(list, page, size);

	}

	@GetMapping(value = "/get/all", produces = "application/json")
	public ResponseEntity<Page<Product>> findAll(@RequestParam(value = "page") Integer page,
			@RequestParam(value = "size") Integer size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<Product> products = productServiceImplementation.findAll(pageable);

		return !products.isEmpty() ? ResponseEntity.ok(products) : ResponseEntity.noContent().build();

	}

	@GetMapping(value = "/get/price", produces = "application/json")
	public ResponseEntity<List<Product>> findByPrice(@RequestParam(defaultValue = "0") BigDecimal min,
			@RequestParam(defaultValue = "9999999999999") BigDecimal max) {

		List<Product> products = productServiceImplementation.findByPrice(min, max);

		return !products.isEmpty() ? ResponseEntity.ok(products)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping(value = "/get/rating", produces = "application/json")
	public ResponseEntity<List<Product>> findByRating(@RequestParam(defaultValue = "0") int min,
			@RequestParam(defaultValue = "10") int max) {

		List<Product> products = productServiceImplementation.findByRating(min, max);

		return !products.isEmpty() ? ResponseEntity.ok(products)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping(value = "/get/category", produces = "application/json")
	public ResponseEntity<Page<Product>> findByCategory(@RequestParam(defaultValue = "") List<String> categories,
			@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {

		// takes a list of category and combine the result of those together.

		List<Product> products = new ArrayList<>();

		for (String s : categories) {
			List<Product> p = productServiceImplementation.findByCategory(categoryServiceImplementation.findByName(s));
			products.addAll(p);
		}

		// remove duplicates.

		List<Product> productsNoDuplicates = new ArrayList<>();

		for (Product product : products) {
			if (!productsNoDuplicates.contains(product)) {

				productsNoDuplicates.add(product);
			}
		}

		// returns list of products without duplicates.

		return PageUtils.listToPageResponseEntity(productsNoDuplicates, page, size);
	}

	// update

	@PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Product> update(@RequestBody Product product) throws RecordNotFoundException {

		if (product != null) {

			Product newProduct = product;

			// initialize fields with default values

			// update to database
			try {
				productServiceImplementation.update(newProduct);
				return ResponseEntity.ok(newProduct);
			} catch (Exception e) {
				throw new RecordNotFoundException();
			}
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	// delete

}
