package com.shopifyapp.service;

import java.time.LocalDate;
import java.util.List;

import com.shopifyapp.exceptions.AddressException;
import com.shopifyapp.exceptions.CartException;
import com.shopifyapp.exceptions.CustomerException;
import com.shopifyapp.exceptions.LoginException;
import com.shopifyapp.exceptions.OrderException;
import com.shopifyapp.model.OrderDTO;
import com.shopifyapp.model.Orders;

public interface OrderService {

	public Orders addOrder(Orders order, String key) throws OrderException, CartException, LoginException;
	public Orders updateOrder(Orders order, String key) throws OrderException, LoginException;
	public Orders removeOrder(Integer oriderId, String key) throws OrderException;
	public Orders viewOrder(Integer orderId) throws OrderException;
	public List<Orders> viewAllOrdersByDate(LocalDate date) throws OrderException;
	public List<Orders> viewAllOrdersByLocation(String city) throws OrderException, AddressException;
	public List<Orders> viewAllOrdersByUserId(String userid) throws OrderException;
}
