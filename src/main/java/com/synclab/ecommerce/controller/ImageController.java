package com.synclab.ecommerce.controller;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.synclab.ecommerce.model.Cart;
import com.synclab.ecommerce.model.Image;
import com.synclab.ecommerce.service.Image.ImageServiceImplementation;

@RestController()
@RequestMapping("/image")
public class ImageController {

	@Autowired
	ImageServiceImplementation imageServiceImplementation;

	// post

	@PostMapping(value = "/insert")
	public ResponseEntity<Image> insert(@RequestPart(value = "file") MultipartFile file) throws Exception {

		if (file == null)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		SerialBlob blob = new SerialBlob(file.getBytes());
		Image image = new Image();
		image.setFileName(file.getOriginalFilename());
		image.setMimetype(file.getContentType());
		image.setFileSize(file.getSize());
		image.setFile(blob);

		// add entity to db
		image = imageServiceImplementation.insert(image);

		return ResponseEntity.ok(image);

	}

	// delete

	@DeleteMapping(value = "/delete/{id}", produces = "application/json")
	public ResponseEntity<Image> deleteById(@PathVariable(value = "id") Long id) {

		imageServiceImplementation.deleteById(id);

		Image entity = imageServiceImplementation.findById(id);

		return entity == null ? ResponseEntity.ok(entity)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

}
