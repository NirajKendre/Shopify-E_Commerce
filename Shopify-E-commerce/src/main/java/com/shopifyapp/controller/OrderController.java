package com.shopifyapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.shopifyapp.exceptions.AddressException;
import com.shopifyapp.exceptions.CartException;
import com.shopifyapp.exceptions.LoginException;
import com.shopifyapp.exceptions.OrderException;

import com.shopifyapp.model.Orders;
import com.shopifyapp.service.OrderService;

@RestController
public class OrderController {
	
	
	@Autowired
    private  OrderService orderService;
	
//	@Autowired
//	private CustomerDao customerDao;
    
    @PostMapping("/orders")
    public ResponseEntity<Orders> addOrderHandler(@RequestBody Orders order,@RequestParam String key) throws OrderException, CartException, LoginException{
    	
    	Orders addedOrder = orderService.addOrder(order, key);
    	
    	return new ResponseEntity<Orders>(addedOrder, HttpStatus.CREATED);
    }
    
    @PutMapping("/orders")
    public ResponseEntity<Orders> updateOrderHandler(@RequestBody Orders order, @RequestParam String key) throws OrderException, LoginException{
    	
    	Orders updatedOrder = orderService.updateOrder(order, key);
    	
    	return new ResponseEntity<Orders>(updatedOrder, HttpStatus.CREATED);
    	
    }
    
    @DeleteMapping("/orders/{orderid}")
    public ResponseEntity<Orders> removeOrderHandler(@PathVariable("orderid") Integer orderId, @RequestParam String uuid) throws OrderException{
    	
    	Orders deletedOrder = orderService.removeOrder(orderId, uuid);
    	
    	return new ResponseEntity<Orders>(deletedOrder, HttpStatus.OK);
    	
    }
    
    @GetMapping("/orders")
    public ResponseEntity<Orders> viewOrderHandler(@RequestParam Integer orderId) throws OrderException{
    	
    	Orders existingOrder = orderService.viewOrder(orderId);
    	
    	return new ResponseEntity<Orders>(existingOrder, HttpStatus.OK);
    	
    }
    
    @GetMapping("/allorders/date/{date}")
    public ResponseEntity<List<Orders>> getAllOrdersBydateHandler(@PathVariable("date") LocalDate date) throws OrderException{
    	
    	List<Orders> orders = orderService.viewAllOrdersByDate(date);
    	
    	return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
    	
    }
    
    @GetMapping("/allorders/loc/{city}")
    public ResponseEntity<List<Orders>> getAllOrdersByLocationHandler(@PathVariable("city") String city) throws OrderException, AddressException{
    	
    	List<Orders> orders = orderService.viewAllOrdersByLocation(city);
    	
    	return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
    	
    }
    
    @GetMapping("/allorders")
    public ResponseEntity<List<Orders>> getAllOrdersByUserIdHandler(@RequestParam String userId) throws OrderException{
    	    	
    	List<Orders> orders = orderService.viewAllOrdersByUserId(userId);
    	
    	return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
    	
    }
}
