package com.shopifyapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopifyapp.exceptions.CartException;
import com.shopifyapp.exceptions.LoginException;
import com.shopifyapp.exceptions.ProductException;
import com.shopifyapp.model.Cart;
import com.shopifyapp.model.ProductDTO;
import com.shopifyapp.service.CartService;




@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;




    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestParam Integer productId , 
    									  @RequestParam Integer quantity,
    									  @RequestParam String key) throws CartException, LoginException, ProductException{

        Cart cartItem = cartService.addProductToCart(productId, quantity, key) ;

        return new ResponseEntity<>(cartItem, HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProductInCart(@RequestParam String key) throws CartException, LoginException {
    	
    	List<ProductDTO> list = cartService.viewAllProducts(key) ;
    	
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    
    
    @DeleteMapping("/products/remove") 
    public ResponseEntity<List<ProductDTO>> removeAProductFromCart(@RequestParam Integer productId , 
    									      		   @RequestParam String key) throws CartException, ProductException, LoginException {
    	
    	List<ProductDTO> list =cartService.removeProductFromCart(productId, key);
    	
    	return new ResponseEntity<>(list, HttpStatus.OK);
    	
    }
    
    @PutMapping("/products") 
    public ResponseEntity<List<ProductDTO>> updateProductQuantity(
												    		  @RequestParam Integer productId , 
															  @RequestParam Integer quantity,
															  @RequestParam String key) throws CartException, LoginException, ProductException {
    
    	List<ProductDTO> list = cartService.updateProductQuantity(productId, quantity, key);
    	
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    
    @DeleteMapping("/products")
    public ResponseEntity<Cart> removeAllProducts(@RequestParam String key) throws CartException, LoginException {
    	Cart cart = cartService.removeAllProducts(key);
    	
    	return new ResponseEntity<>(cart, HttpStatus.OK);
    }
    
}
