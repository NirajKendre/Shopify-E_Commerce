package com.shopifyapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopifyapp.exceptions.CustomerException;
import com.shopifyapp.exceptions.LoginException;
import com.shopifyapp.model.Customer;
import com.shopifyapp.service.CustomerService;


@CrossOrigin(origins = "*")
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> addCustomerHandler(@Valid @RequestBody Customer customer) throws CustomerException{

		Customer addedCustomer = customerService.addCustomer(customer);
		
		 return new ResponseEntity<Customer>(addedCustomer, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/customers/{key}")
	public ResponseEntity<Customer> updateCustomerHandler(@PathVariable("key") String key,@RequestBody Customer customer) throws LoginException, CustomerException{
		
		Customer updatedCustomer = customerService.updateCustomer(customer, key);
		
		return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/customers/{key}")
	public ResponseEntity<Customer> removeCustomerHandler(@PathVariable("key") String key,@RequestBody Customer customer) throws CustomerException, LoginException, LoginException{
		
		Customer deletedCustomer = customerService.removeCustomer(customer, key);
		
		return new ResponseEntity<Customer>(deletedCustomer, HttpStatus.OK);
		
	}
	
	@GetMapping("/customers/{customerid}")
	public ResponseEntity<Customer> getCustomerHandler(@PathVariable("customerid") Integer customerId,@PathVariable("key")String key) throws CustomerException,LoginException{
		
		Customer existingCustomer = customerService.viewCustomer(customerId,key);
		
		return new ResponseEntity<Customer>(existingCustomer, HttpStatus.OK);
		
	}
	

	
}
