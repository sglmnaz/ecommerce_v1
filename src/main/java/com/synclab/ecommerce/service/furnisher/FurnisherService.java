package com.synclab.ecommerce.service.furnisher;

import com.synclab.ecommerce.model.Furnisher;

import java.util.List;

public interface FurnisherService {

    //insert
    Furnisher insert(Furnisher entity);

    //retrieve
    Furnisher findById(Long id);

    List<Furnisher> findAll();

    //update
    Furnisher update(Furnisher entity);

    //delete
    void deleteById(Long id);

}
