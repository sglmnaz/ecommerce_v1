package com.synclab.ecommerce.service.Image;


import com.synclab.ecommerce.model.Image;

public interface ImageService {
	
	//insert
	void insert(Image image);
	//retrieve
	Image findById(Long id);
	//update
	void update(Image image);
	//delete
	void deleteById(Long id);
	void deleteAll();

}
