package com.synclab.ecommerce.service.warehouse;

import com.synclab.ecommerce.model.Warehouse;
import com.synclab.ecommerce.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImplementation implements WarehouseService {

    @Autowired
    private WarehouseRepository repository;

    @Override
    public Warehouse insert(Warehouse entity) {
        return repository.save(entity);
    }

    @Override
    public Warehouse findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Warehouse> findAll() {
        return repository.findAll();
    }

    @Override
    public Warehouse update(Warehouse entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}