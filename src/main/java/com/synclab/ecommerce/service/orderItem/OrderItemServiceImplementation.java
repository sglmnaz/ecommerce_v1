//package com.synclab.ecommerce.service.orderItem;
//
//import com.synclab.ecommerce.model.OrderItem;
//import com.synclab.ecommerce.repository.OrderItemRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class OrderItemServiceImplementation implements OrderItemService {
//
//    @Autowired
//    private OrderItemRepository repository;
//
//
//    @Override
//    public OrderItem insert(OrderItem record) {
//        return repository.save(record);
//    }
//
//    @Override
//    public OrderItem findById(String id) {
//        return repository.findById(id).get();
//    }
//
//    @Override
//    public List<OrderItem> findByOrder_OrderId(String orderId) {
//        return repository.findByOrder_Id(orderId);
//    }
//
//    @Override
//    public List<OrderItem> findAll() {
//        return repository.findAll();
//    }
//
//    @Override
//    public OrderItem update(OrderItem record) {
//        return repository.save(record);
//    }
//
//    @Override
//    public void deleteById(String id) {
//        repository.deleteById(id);
//    }
//
//    @Override
//    public void deleteAll() {
//        repository.deleteAll();
//    }
//
//}
