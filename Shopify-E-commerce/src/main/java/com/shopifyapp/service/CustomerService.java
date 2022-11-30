package com.shopifyapp.service;

import java.util.List;

import com.shopifyapp.exceptions.CustomerException;
import com.shopifyapp.exceptions.LoginException;
import com.shopifyapp.exceptions.ProductException;
import com.shopifyapp.model.Customer;
import com.shopifyapp.model.Product;

public interface CustomerService {

	public Customer addCustomer(Customer cust) throws CustomerException ;
	
	public Customer updateCustomer(Customer cust, String key) throws CustomerException, LoginException, LoginException  ;
	
	public Customer removeCustomer(Customer cust, String key) throws CustomerException, LoginException, LoginException ;
	
	public Customer viewCustomer(Integer customerId,String key)  throws CustomerException,LoginException;
	
}