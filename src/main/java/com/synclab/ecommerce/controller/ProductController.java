package com.synclab.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.synclab.ecommerce.model.Category;
import com.synclab.ecommerce.model.Product;
import com.synclab.ecommerce.service.category.CategoryServiceImplementation;
import com.synclab.ecommerce.service.product.ProductServiceImplementation;
import com.synclab.ecommerce.utility.exception.RecordNotFoundException;

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
	public ResponseEntity<Product> insert(@RequestBody Product product) {

		if (product == null) { 
			//product is empty, return error message
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		Product newProduct = product;
		List<Category> categories = newProduct.getCategories();
		List<Category> newCategories = new ArrayList<Category>();

		// check if category is existing
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

		return newProduct != null ? ResponseEntity.ok(newProduct)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	@GetMapping(value = "/get/all", produces = "application/json")
	public ResponseEntity<List<Product>> findAll() {

		List<Product> products = productServiceImplementation.findAll();

		return !products.isEmpty() ? ResponseEntity.ok(products)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

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
	public ResponseEntity<List<Product>> findByCategory(@RequestParam(defaultValue = "") List<String> categories) {

		System.out.println("Searching for products in: " + categories.toString());

		// takes a list of category and combine the result of those together.

		List<Product> products = new ArrayList<Product>();

		for (String s : categories) {
			List<Product> p = productServiceImplementation.findByCategory(categoryServiceImplementation.findByName(s));
			products.addAll(p);
		}

		// remove duplicates.

		List<Product> productsNoDuplicates = new ArrayList<Product>();

		for (Product product : products) {
			if (!productsNoDuplicates.contains(product)) {

				productsNoDuplicates.add(product);
			}
		}

		// returns list of products without duplicates.

		return !productsNoDuplicates.isEmpty() ? ResponseEntity.ok(productsNoDuplicates)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
				;
				return ResponseEntity.ok(newProduct);
			} catch (Exception e) {
				throw new RecordNotFoundException();
			}
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	// delete

	@DeleteMapping(value = "/delete/all", produces = "application/json")
	public ResponseEntity<List<Product>> deleteAll() {

		productServiceImplementation.deleteAll();

		List<Product> products = productServiceImplementation.findAll();

		return products.isEmpty() ? ResponseEntity.ok(products)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

}
