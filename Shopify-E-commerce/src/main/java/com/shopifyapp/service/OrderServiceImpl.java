package com.shopifyapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopifyapp.Repositery.CartDao;
import com.shopifyapp.Repositery.CurrentUserSessionDao;
import com.shopifyapp.Repositery.CustomerDao;
import com.shopifyapp.Repositery.OrderDao;
import com.shopifyapp.exceptions.AddressException;
import com.shopifyapp.exceptions.CartException;
import com.shopifyapp.exceptions.LoginException;
import com.shopifyapp.exceptions.OrderException;
import com.shopifyapp.login.CurrentUserSession;
import com.shopifyapp.model.Address;
import com.shopifyapp.model.AddressDTO;
import com.shopifyapp.model.Cart;
import com.shopifyapp.model.Customer;
import com.shopifyapp.model.Orders;
import com.shopifyapp.model.ProductDTO;


@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao oDao;
	
	@Autowired
	CurrentUserSessionDao uSessionDao;
	
	@Autowired
	CustomerDao cDao;
	
	@Autowired
	CartDao cartDao;

	@Override
	public Orders addOrder(Orders order, String key) throws OrderException, CartException, LoginException {
		
		 Optional<CurrentUserSession> user = uSessionDao.findByUuid(key);
		 
		 if( user.isPresent() ) {
			 
			 Integer customerId = user.get().getCustomerId();
			 
			 Optional<Customer> ourCustomer = cDao.findById(customerId);
			 
			 Address addr = ourCustomer.get().getAddress();
			 
			 
			 Orders currOrder = new Orders();
			 
			 currOrder.setOrderDate(LocalDate.now());
			 currOrder.setOrderAddress(new AddressDTO(addr.getStreetNo(), addr.getBuildingName(), addr.getCity(), addr.getState(), addr.getCountry(), addr.getPincode()));
			 
			 currOrder.setCustomer(ourCustomer.get());
			 currOrder.setOrderStatus("Order confirmed");
			 
			 //List<ProductDto> list = cartRepo.getCart(customerId).getProducts();
			 
			 List<ProductDTO> list = cartDao.findByCustomer(ourCustomer.get()).getProducts();
			 
			 if( list.size() < 1) {
				 throw new CartException("Add product to the cart first...");
			 }
			 
			 List<ProductDTO> productList = new ArrayList<>();

			 Double total = 0.0 ;
			 
			 for(ProductDTO proDto : list) {
				 
				 productList.add(proDto);
				 
				 total += (proDto.getPrice() * proDto.getQuantity()) ;
				 
			 }
			 
			 currOrder.setTotal(total);	
			 currOrder.setPList(productList); 
			 
			 Cart customerCart = cartDao.findByCustomer(ourCustomer.get()) ;
			 
			 customerCart.setProducts(new ArrayList<>());
			 
			 cartDao.save(customerCart);
			 
			 return oDao.save(currOrder);
			 
		 }
		 else {
			 throw new LoginException("Login first");
		 }
		 
		 
	}

	@Override
	public Orders updateOrder(Orders order, String key) throws OrderException, LoginException {
		
		if( uSessionDao.findByUuid(key) != null ) {
		
			Optional<Orders> opt=  oDao.findById(order.getOrderId());
			
			if(opt.isPresent()) {
				return oDao.save(order);
			}
			else {
				throw new OrderException("Order does not exist");
			}
		}
		else {
			throw new LoginException("Please, Login First...");
		}
		
	}

	@Override
	public Orders removeOrder(Integer orderId, String key) throws OrderException {
		
		Orders	existingProduct = oDao.findById(orderId).orElseThrow(()->new OrderException("Order does not exist with id :"));
		
		oDao.delete(existingProduct);
		
		return existingProduct;
	}

	@Override
	public Orders viewOrder(Integer orderId) throws OrderException {
		
		Optional<Orders> opt1=  oDao.findById(orderId);
		
		if(opt1.isPresent()) {
			return opt1.get();
		}
		else {
			throw new OrderException("No order found");
		}
	}

	@Override
	public List<Orders> viewAllOrdersByDate(LocalDate date) throws OrderException {
		List<Orders> orders= oDao.findByOrderDate(date);
		
		if(orders.size()>0) {
			
			return orders;
		}
		else {
			throw new OrderException("Order doesn't exist on this date.");
		}
		
	}

	@Override
	public List<Orders> viewAllOrdersByLocation(String loc) throws OrderException, AddressException {
		
		List<Orders> list= oDao.getOrderByCity(loc);
		
		if( list.size() < 1) {
			throw new OrderException("No order found with this userId.");
		}
		return list;
	}

	@Override
	public List<Orders> viewAllOrdersByUserId(String userid) throws OrderException {

		List<Orders> list = oDao.getOrdersByUserId(userid);
		
		if( list.size() < 1) {
			throw new OrderException("No order found with this userId.");
		}
		
		return list;
	}
	
}
