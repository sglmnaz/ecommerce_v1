package com.synclab.ecommerce.service.stock;

import com.synclab.ecommerce.model.Stock;
import com.synclab.ecommerce.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImplementation implements StockService {

    @Autowired
    private StockRepository repository;

    @Override
    public Stock insert(Stock entity) {
        return repository.save(entity);
    }

    @Override
    public Stock findById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Stock> findAll() {
        return repository.findAll();
    }

    @Override
    public Stock update(Stock entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

}
