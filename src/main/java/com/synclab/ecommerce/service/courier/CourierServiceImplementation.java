package com.synclab.ecommerce.service.courier;

import com.synclab.ecommerce.model.Courier;
import com.synclab.ecommerce.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierServiceImplementation implements CourierService {

    @Autowired
    private CourierRepository repository;

    @Override
    public Courier insert(Courier entity) {
        return repository.save(entity);
    }

    @Override
    public Courier findById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Courier> findAll() {
        return repository.findAll();
    }

    @Override
    public Courier update(Courier entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

}
