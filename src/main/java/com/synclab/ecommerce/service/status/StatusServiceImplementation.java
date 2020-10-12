package com.synclab.ecommerce.service.status;

import com.synclab.ecommerce.model.Status;
import com.synclab.ecommerce.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImplementation implements StatusService {

    @Autowired
    private StatusRepository repository;

    @Override
    public Status findByName(String name) {
        return repository.findByName(name);
    }

}
