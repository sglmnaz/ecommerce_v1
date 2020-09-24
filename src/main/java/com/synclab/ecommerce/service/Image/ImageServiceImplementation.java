package com.synclab.ecommerce.service.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synclab.ecommerce.model.Image;
import com.synclab.ecommerce.repository.ImageRepository;

@Service
public class ImageServiceImplementation implements ImageService{

	@Autowired
	private ImageRepository repository;
	
	@Override
	public void insert(Image image) {
		repository.save(image);
	}

	@Override
	public Image findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public void update(Image image) {
		repository.save(image);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

}
