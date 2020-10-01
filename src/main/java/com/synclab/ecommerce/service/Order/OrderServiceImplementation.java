package com.synclab.ecommerce.service.Order;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import com.synclab.ecommerce.model.Order;
import com.synclab.ecommerce.repository.OrderRepository;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;

@Service
public class OrderServiceImplementation implements OrderService {

	@Autowired
	private OrderRepository repository;

	@Override
	public Order insert(Order entity) {
		return repository.save(entity);
	}

	@Override
	public Order findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public Order update(Order entity) {
		return repository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	

	@Override
	public List<Order> findByUser_UserId(Long id) {
		return repository.findByUser_UserId(id);
	}

	@Override
	public List<Order> findAll() {
		return repository.findAll();
	}
	
	// RSQL

	@Autowired
	private EntityManager entityManager;

	public List<Order> rsqlQuery(String queryString) {
		// We will need a JPA EntityManager

		// Create the JPA Visitor
		RSQLVisitor<CriteriaQuery<Order>, EntityManager> visitor = new JpaCriteriaQueryVisitor<Order>();

		// Parse a RSQL into a Node
		Node rootNode = new RSQLParser().parse(queryString);

		// Visit the node to retrieve CriteriaQuery
		CriteriaQuery<Order> query = rootNode.accept(visitor, entityManager);

		// Execute and get results
		List<Order> entities = entityManager.createQuery(query).getResultList();
		return entities;
	}

	


}
