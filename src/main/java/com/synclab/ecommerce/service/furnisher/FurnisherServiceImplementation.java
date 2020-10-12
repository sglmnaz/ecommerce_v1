package com.synclab.ecommerce.service.furnisher;

import com.synclab.ecommerce.model.Furnisher;
import com.synclab.ecommerce.repository.FurnisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FurnisherServiceImplementation implements FurnisherService {

    @Autowired
    private FurnisherRepository repository;

    @Override
    public Furnisher insert(Furnisher entity) {
        return repository.save(entity);
    }

    @Override
    public Furnisher findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Furnisher> findAll() {
        return repository.findAll();
    }

    @Override
    public Furnisher update(Furnisher entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
