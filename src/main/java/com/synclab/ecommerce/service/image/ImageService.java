package com.synclab.ecommerce.service.image;


import com.synclab.ecommerce.model.Image;

public interface ImageService {

    //insert
    Image insert(Image image);

    //retrieve
    Image findById(String id);

    //update
    Image update(Image image);

    //delete
    void deleteById(String id);

    void deleteAll();

}
