package com.synclab.ecommerce.controller;

import com.synclab.ecommerce.model.Cart;
import com.synclab.ecommerce.model.CartItem;
import com.synclab.ecommerce.model.Product;
import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.service.cart.CartServiceImplementation;
import com.synclab.ecommerce.service.cartItem.CartItemServiceImplementation;
import com.synclab.ecommerce.service.product.ProductServiceImplementation;
import com.synclab.ecommerce.service.user.UserServiceImplementation;
import com.synclab.ecommerce.utility.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    // fields

    @Autowired
    private CartServiceImplementation cartServiceImplementation;

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    private ProductServiceImplementation productServiceImplementation;

    @Autowired
    private CartItemServiceImplementation cartItemServiceImplementation;

    // post

    @PostMapping(value = "/insert/{userId}", produces = "application/json")
    public ResponseEntity<Cart> insert(@PathVariable(value = "userId") Long userId) {

        User user = userServiceImplementation.findById(userId);

        if (user == null) // return error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        // declarations
        Cart entity = new Cart(BigDecimal.ZERO, 0);
        entity.setUser(user);

        // add to database
        cartServiceImplementation.insert(entity);

        return ResponseEntity.ok(entity);

    }

    // get

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<Cart> findById(@PathVariable(value = "id") Long id) {

        Cart entity = cartServiceImplementation.findById(id);

        return entity != null ? ResponseEntity.ok(entity)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @GetMapping(value = "/getByUser/{userId}", produces = "application/json")
    public ResponseEntity<Cart> findByUser(@PathVariable(value = "userId") Long userId) {

        User user = userServiceImplementation.findById(userId);
        Cart entity = cartServiceImplementation.findByUser(user);

        return entity != null ? ResponseEntity.ok(entity)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @GetMapping(value = "/getAllProducts/{id}", produces = "application/json")
    public ResponseEntity<List<CartItem>> findAllProductsById(@PathVariable(value = "id") Long id) {

        Cart entity = cartServiceImplementation.findById(id);

        return entity != null ? ResponseEntity.ok(cartItemServiceImplementation.findByCart_CartId(id))
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @GetMapping(value = "/insertProduct", produces = "application/json")
    public ResponseEntity<String> addProduct(@RequestParam(name = "cartId") Long cartId,
                                             @RequestParam(name = "productId") Long productId,
                                             @RequestParam(name = "productQuantity", defaultValue = "1") Integer productQuantity) {

        Cart cart = cartServiceImplementation.findById(cartId);
        Product product = productServiceImplementation.findById(productId);
        CartItem cartItem = new CartItem(cart, product, productQuantity);

        cartItem = cartItemServiceImplementation.insert(cartItem);
        cartServiceImplementation.update(cart);
        evaluateTotals(cart.getCartId());

        return cartItem != null
                ? ResponseEntity.ok("product: " + product.getName() + " x" + productQuantity + " was added to cart")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @GetMapping(value = "/changeProductQuantity", produces = "application/json")
    public ResponseEntity<String> changeProductQuantity(@RequestParam(name = "cartId") Long cartId,
                                                        @RequestParam(name = "productId") Long productId,
                                                        @RequestParam(name = "productQuantity", defaultValue = "1") Integer productQuantity) {

        Cart cart = cartServiceImplementation.findById(cartId);
        List<CartItem> items = cartItemServiceImplementation.findByCart_CartId(cartId);

        CartItem cartItem = null;

        for (CartItem item : items) {
            if (item.getProduct().getProductId() == productId) {
                cartItem = item;
            }
        }

        if (cartItem == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        cartItem.setQuantity(productQuantity);

        cartItem = cartItemServiceImplementation.update(cartItem);

        cartServiceImplementation.update(cart);
        evaluateTotals(cart.getCartId());

        return cartItem != null ? ResponseEntity.ok("product quantity set to: " + productQuantity)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    // update

    @PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Cart> update(@RequestBody Cart request) throws RecordNotFoundException {

        if (request == null) // return error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        if (cartServiceImplementation.findById(request.getCartId()) == null) // no such object is present in the
            // repository
            throw new RecordNotFoundException();

        // declarations
        Cart entity = request;

        // operations

        // add to database
        cartServiceImplementation.insert(entity);

        return ResponseEntity.ok(entity);

    }

    // delete

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Cart> deleteById(@PathVariable(value = "id") Long id) {

        cartServiceImplementation.deleteById(id);

        Cart entity = cartServiceImplementation.findById(id);

        return entity == null ? ResponseEntity.ok(entity)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    // utility

    public BigDecimal evaluateTotalPrice(List<CartItem> items) {

        BigDecimal total = BigDecimal.ZERO;

        if (items == null)
            return total;

        for (CartItem item : items) {
            BigDecimal itemPrice = item.getProduct().getPrice();
            BigDecimal itemQuantity = BigDecimal.valueOf(item.getQuantity());
            BigDecimal temp = (itemQuantity.multiply(itemPrice));
            total = total.add(temp);
        }

        return total;
    }

    public void evaluateTotals(Long cartId) {

        Cart cart = cartServiceImplementation.findById(cartId);
        List<CartItem> items = cartItemServiceImplementation.findByCart_CartId(cartId);
        Integer total = 0;

        if (items == null)
            cart.setTotalItems(total);

        for (CartItem item : items) {
            total += item.getQuantity();
        }

        cart.setTotalItems(total);
        cart.setTotalPrice(evaluateTotalPrice(items));

        cartServiceImplementation.insert(cart);

    }

}
