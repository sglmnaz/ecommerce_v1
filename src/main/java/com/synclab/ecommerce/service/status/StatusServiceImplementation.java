package com.synclab.ecommerce.service.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synclab.ecommerce.model.Status;
import com.synclab.ecommerce.repository.StatusRepository;

@Service
public class StatusServiceImplementation implements StatusService{
	
	@Autowired
	private StatusRepository repository;
	
	@Override
	public Status findByName(String name) {
		return repository.findByName(name);
	}

}
