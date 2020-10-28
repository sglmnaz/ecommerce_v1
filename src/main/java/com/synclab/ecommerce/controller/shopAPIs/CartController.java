package com.synclab.ecommerce.controller.shopAPIs;

import com.synclab.ecommerce.model.Cart;
import com.synclab.ecommerce.model.CartItem;
import com.synclab.ecommerce.model.Product;
import com.synclab.ecommerce.service.cart.CartServiceImplementation;
import com.synclab.ecommerce.service.product.ProductServiceImplementation;
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
    private ProductServiceImplementation productServiceImplementation;

    // get

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<Cart> findById(@PathVariable(value = "id") String id) {

        Cart entity = cartServiceImplementation.findById(id);

        return entity != null ? ResponseEntity.ok(entity)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @GetMapping(value = "/get/products/{id}", produces = "application/json")
    public ResponseEntity<List<CartItem>> findAllProductsById(@PathVariable(value = "id") String id) {

        Cart cart = cartServiceImplementation.findById(id);

        return cart != null ? ResponseEntity.ok(cart.getItems())
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @PostMapping(value = "/insert/product", produces = "application/json")
    public ResponseEntity<String> addProduct(@RequestParam(name = "cartId") String cartId,
                                             @RequestParam(name = "productId") String productId,
                                             @RequestParam(name = "productQuantity", defaultValue = "1") Integer productQuantity) {

        Cart cart = cartServiceImplementation.findById(cartId);
        Product product = productServiceImplementation.findById(productId);
        CartItem item = new CartItem(product, productQuantity);
        cart.getItems().add(item);
        cart = cartServiceImplementation.update(cart);
        evaluateTotals(cart);

        return item != null
                ? ResponseEntity.ok("product: " + product.getName() + " x" + productQuantity + " was added to cart")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @PatchMapping(value = "/changeProductQuantity", produces = "application/json")
    public ResponseEntity<String> changeProductQuantity(@RequestParam(name = "cartId") String cartId,
                                                        @RequestParam(name = "productId") String productId,
                                                        @RequestParam(name = "productQuantity", defaultValue = "1") Integer productQuantity) {

        Cart cart = cartServiceImplementation.findById(cartId);
        List<CartItem> items = cart.getItems();

        CartItem item = null;

        for (CartItem _item : items) {
            if (_item.getProduct().getId() == productId) {
                item = _item;
            }
        }

        if (item == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        cart.getItems().remove(item);
        item.setQuantity(productQuantity);
        cart.getItems().add(item);

        cartServiceImplementation.update(cart);
        evaluateTotals(cart);

        return item != null ? ResponseEntity.ok("product quantity set to: " + productQuantity)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    // update

    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Cart> update(@RequestBody Cart request) {

        if (request == null) // return error message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        if (cartServiceImplementation.findById(request.getId()) == null) // no such object is present in the
            // repository
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        // declarations
        Cart cart = request;
        evaluateTotals(cart);

        // add to database
        cart = cartServiceImplementation.insert(cart);

        return ResponseEntity.ok(cart);

    }
    
    // patch

    @PutMapping(value = "/empty/{id}", produces = "application/json")
    public ResponseEntity<Cart> empty(@PathVariable(value = "id") String id) {

        if (id == null) // return error message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Cart cart = cartServiceImplementation.findById(id);
        
        if (cart == null) // no cart found
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        cartServiceImplementation.emptyCart(cart);
        evaluateTotals(cart);

        // add to database
        cart = cartServiceImplementation.insert(cart);

        return ResponseEntity.ok(cart);

    }

    // delete

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Cart> deleteById(@PathVariable(value = "id") String id) {

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

    public void evaluateTotals(Cart _cart) {

        Cart cart = _cart;
        List<CartItem> items = cart.getItems();
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
