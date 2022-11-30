package com.shopifyapp.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopifyapp.model.Cart;
import com.shopifyapp.model.Customer;
@Repository
public interface CartDao extends JpaRepository<Cart, Integer>{

    
	@Query("select c from Cart c where c.customer.customerId=?1")
	public Cart getCart(Integer custId);
	
	public Cart findByCustomer(Customer customer);
	
}