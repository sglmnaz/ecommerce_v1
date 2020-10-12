package com.synclab.ecommerce.service.shipping;

import com.synclab.ecommerce.model.Shipping;
import com.synclab.ecommerce.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingServiceImplementation implements ShippingService {

    @Autowired
    private ShippingRepository repository;

    @Override
    public Shipping insert(Shipping record) {
        return repository.save(record);
    }

    @Override
    public Shipping findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Shipping> findAll() {
        return repository.findAll();
    }

    @Override
    public Shipping update(Shipping record) {
        return repository.save(record);
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
