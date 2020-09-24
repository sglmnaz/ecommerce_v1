package com.synclab.ecommerce.service.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synclab.ecommerce.model.Category;
import com.synclab.ecommerce.model.Product;
import com.synclab.ecommerce.model.Subcategory;
import com.synclab.ecommerce.repository.CategoryRepository;
import com.synclab.ecommerce.repository.ProductRepository;

@Service
public class ProductServiceImplementation implements ProductService{

	@Autowired
	ProductRepository repository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	//insert
	
	@Override
	public void insert(Product product) {
		repository.save(product);
	}

	//retrieve
	
	@Override
	public Product findById(Long id) {
		return repository.findById(id).get();
	}
	
	@Override
	public List<Product> findAll() {
		return repository.findAll();
	}
	
	@Override
	public List<Product> findByCategory(Category category) {
		return repository.findByCategories(category);
	}

	@Override
	public List<Product> findBySubcategory(Subcategory subcategory) {
		return repository.findByCategories(categoryRepository.findBySubcategories(subcategory));
	}

	@Override
	public List<Product> findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public List<Product> findByPrice(BigDecimal min, BigDecimal max) {
		List<Product> products = repository.findAll();
		List<Product> matchingProducts = new ArrayList<Product>();
		for(Product product : products)
		{
			if ((product.getPrice().compareTo(min) == -1) || (product.getPrice().compareTo(max) == 1))
			{
				continue;
			}
			else 
			{
				matchingProducts.add(product);
			}
		}
		return matchingProducts;
	}

	@Override
	public List<Product> findByRating(Integer min, Integer max) {
		List<Product> products = repository.findAll();
		List<Product> matchingProducts = new ArrayList<Product>();

		for(Product product : products)
		{
			if ((product.getRating()<min)||(product.getRating()>max))
			{
				continue;
			}
			else 
			{
				matchingProducts.add(product);
			}
		}
		return matchingProducts;
	}
	
	//update

	@Override
	public void update(Product product) {
		repository.save(product);
	}
	
	//delete

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

}
