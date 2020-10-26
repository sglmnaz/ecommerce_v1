package com.synclab.ecommerce.service.subcategory;

import com.synclab.ecommerce.model.Subcategory;
import com.synclab.ecommerce.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubcategoryServiceImplementation implements SubcategoryService {

    @Autowired
    private SubcategoryRepository repository;

    @Override
    public Subcategory insert(Subcategory entity) {
        return repository.save(entity);
    }

    @Override
    public Subcategory findById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Subcategory> findAll() {
        return repository.findAll();
    }

    @Override
    public Subcategory update(Subcategory entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

}

