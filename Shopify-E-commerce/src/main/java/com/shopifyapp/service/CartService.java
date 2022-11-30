package com.shopifyapp.service;

import java.util.List;

import com.shopifyapp.model.ProductDTO;
import com.shopifyapp.exceptions.CartException;
import com.shopifyapp.exceptions.ProductException;
import com.shopifyapp.exceptions.LoginException;
import com.shopifyapp.model.Cart;

public interface CartService {

	
	public Cart addProductToCart(Integer productId, int quantity,String key) throws CartException, LoginException, ProductException ;
	
	public List<ProductDTO> removeProductFromCart(Integer productId,String key) throws CartException, ProductException, LoginException  ;
	
	public List<ProductDTO> updateProductQuantity(Integer productId,Integer quantity,String key) throws CartException, LoginException, ProductException ;
	
	public Cart removeAllProducts(String key) throws CartException, LoginException ;
	
	public List<ProductDTO> viewAllProducts(String key)  throws CartException, LoginException;
 
	

}
