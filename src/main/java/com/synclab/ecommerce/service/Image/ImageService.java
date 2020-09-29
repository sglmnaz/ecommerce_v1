package com.synclab.ecommerce.service.Image;



import com.synclab.ecommerce.model.Image;

public interface ImageService {
	
	//insert
	Image insert(Image image);
	//retrieve
	Image findById(Long id);
	//update
	Image update(Image image);
	//delete
	void deleteById(Long id);
	void deleteAll();

}
