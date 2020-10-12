package com.synclab.ecommerce.service.product;

import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import com.synclab.ecommerce.model.Category;
import com.synclab.ecommerce.model.Product;
import com.synclab.ecommerce.model.Subcategory;
import com.synclab.ecommerce.repository.CategoryRepository;
import com.synclab.ecommerce.repository.ProductRepository;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;


    // insert
    @Autowired
    private EntityManager entityManager;

    // retrieve

    @Override
    public void insert(Product product) {
        repository.save(product);
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> product = repository.findById(id);
        return product.isEmpty()
                ? null
                : product.get();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
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
        for (Product product : products) {
            if ((product.getPrice().compareTo(min) == -1) || (product.getPrice().compareTo(max) == 1)) {
                continue;
            } else {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    // update

    @Override
    public List<Product> findByRating(Integer min, Integer max) {
        List<Product> products = repository.findAll();
        List<Product> matchingProducts = new ArrayList<Product>();

        for (Product product : products) {
            if ((product.getRating() < min) || (product.getRating() > max)) {
                continue;
            } else {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    // delete

    @Override
    public void update(Product product) {
        repository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // RSQL

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    public List<Product> rsqlQuery(String queryString) {
        // We will need a JPA EntityManager

        // Create the JPA Visitor
        RSQLVisitor<CriteriaQuery<Product>, EntityManager> visitor = new JpaCriteriaQueryVisitor<Product>();

        // Parse a RSQL into a Node
        Node rootNode = new RSQLParser().parse(queryString);

        // Visit the node to retrieve CriteriaQuery
        CriteriaQuery<Product> query = rootNode.accept(visitor, entityManager);

        // Execute and get results
        List<Product> products = entityManager.createQuery(query).getResultList();
        return products;
    }

}
