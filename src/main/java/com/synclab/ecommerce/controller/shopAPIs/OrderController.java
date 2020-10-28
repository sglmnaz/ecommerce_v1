package com.synclab.ecommerce.controller.shopAPIs;

import com.synclab.ecommerce.model.Cart;
import com.synclab.ecommerce.model.CartItem;
import com.synclab.ecommerce.model.Order;
import com.synclab.ecommerce.model.OrderItem;
import com.synclab.ecommerce.service.cart.CartServiceImplementation;
import com.synclab.ecommerce.service.order.OrderServiceImplementation;
import com.synclab.ecommerce.service.status.StatusServiceImplementation;
import com.synclab.ecommerce.utility.pages.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImplementation orderServiceImplementation;

    @Autowired
    private CartServiceImplementation cartServiceImplementation;

    @Autowired
    private StatusServiceImplementation statusServiceImplementation;

    // post

    // transorms a cart in order
    @PostMapping(value = "/insert/{cartId}", produces = "application/json")
    public ResponseEntity<Order> insert(@PathVariable(value = "cartId") String cartId) {

        Cart cart = cartServiceImplementation.findById(cartId);

        if (cart == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Order order = new Order();
        order.setCreationDate(new Date());
        order.setTotalItems(cart.getTotalItems());
        order.setTotalPrice(cart.getTotalPrice());
        order.setUserId(cart.getUserId());
        order.setStatus(statusServiceImplementation.findByName("STATUS_CREATED"));


        List<CartItem> cartItems = cart.getItems();
        List<OrderItem> orderItems = new ArrayList<OrderItem>();

        for (CartItem item : cartItems) {
            OrderItem orderItem = new OrderItem(item.getProduct(),item.getQuantity());
            orderItems.add(orderItem);
        }
        
        order.setItems(orderItems);
        cart.setItems(new ArrayList<CartItem>());
        cart.setTotalItems(0);
        cart.setTotalPrice(BigDecimal.ZERO);
        
        cartServiceImplementation.update(cart);
        order = orderServiceImplementation.insert(order);
        
        return ResponseEntity.ok(order);
    }

    // get

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<Order> findById(@PathVariable(value = "id") String id) {

        Order entity = orderServiceImplementation.findById(id);

        return entity != null ? ResponseEntity.ok(entity)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

//    @GetMapping(value = "/getFromQuery", produces = "application/json")
//    public ResponseEntity<Page<Order>> findById(@RequestParam String query, @RequestParam(value = "page") Integer page,
//                                                @RequestParam(value = "size") Integer size) {
//
//        List<Order> list = orderServiceImplementation.rsqlQuery(query);
//        return PageUtils.listToPageResponseEntity(list, page, size);
//    }

    @GetMapping(value = "/getForUser/{id}", produces = "application/json")
    public ResponseEntity<Page<Order>> findByUserId(@PathVariable(name = "id") String userId,
                                                    @RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {

        List<Order> list = orderServiceImplementation.findByUser_UserId(userId);
        return PageUtils.listToPageResponseEntity(list, page, size);

    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public ResponseEntity<Page<Order>> findByUserId(@RequestParam(value = "page") Integer page,
                                                    @RequestParam(value = "size") Integer size) {

        List<Order> list = orderServiceImplementation.findAll();
        return PageUtils.listToPageResponseEntity(list, page, size);
    }

    // delete

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Order> deleteById(@PathVariable(value = "id") String id) {

        Order entity = orderServiceImplementation.findById(id);
        orderServiceImplementation.deleteById(id);
        entity = orderServiceImplementation.findById(id);

        return entity == null ? ResponseEntity.ok(entity)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

}
