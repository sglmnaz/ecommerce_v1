package com.synclab.ecommerce.controller.managementAPIs;

import com.synclab.ecommerce.model.Image;
import com.synclab.ecommerce.service.image.ImageServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController()
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageServiceImplementation imageServiceImplementation;

    // post

    @PostMapping(value = "/insert")
    public ResponseEntity<Image> insert(@RequestPart(value = "file") MultipartFile file) throws IOException {

        if (file == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        // SerialBlob blob = new SerialBlob(file.getBytes());
        Image image = new Image();
        image.setFileName(file.getOriginalFilename());
        image.setMimetype(file.getContentType());
        image.setFileSize(file.getSize());
        image.setFile(file.getBytes());

        // add entity to db
        image = imageServiceImplementation.insert(image);

        return ResponseEntity.ok(image);

    }

    // delete

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Image> deleteById(@PathVariable(value = "id") String id) {

        imageServiceImplementation.deleteById(id);

        Image entity = imageServiceImplementation.findById(id);

        return entity == null ? ResponseEntity.ok(entity)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

}
