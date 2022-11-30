package com.shopifyapp.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shopifyapp.Repositery.AddressDao;
import com.shopifyapp.Repositery.CurrentUserSessionDao;
import com.shopifyapp.Repositery.CustomerDao;
import com.shopifyapp.exceptions.CustomerException;
import com.shopifyapp.exceptions.LoginException;
import com.shopifyapp.login.CurrentUserSession;
import com.shopifyapp.model.Customer;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	CurrentUserSessionService currentUserSessionService;
	
	@Autowired
	CurrentUserSessionDao currentUserSessionDao;
	
	@Autowired
	AddressDao addressDao ;
	
	@Override
	public Customer addCustomer(Customer cust) throws CustomerException {
		
		Optional<Customer> opt = customerDao.findByMobileNumber(cust.getMobileNumber()) ;
		
		if(opt.isPresent()) {
			throw new CustomerException("Customer already Exist With this Mobile Number");
		}
		
		return customerDao.save(cust);
	}

	@Override
	public Customer updateCustomer(Customer cust, String key) throws CustomerException, LoginException {
		
		Customer customerDetails = currentUserSessionService.getCustomerDetails(key) ;
		
		if(customerDetails == null) {
			throw new LoginException("No user Found | Login first");
		}else if( cust.getMobileNumber().toCharArray().length != 10 ){
			
			throw new CustomerException("Mobile Number can only be of 10 digit");
		}
		
		if(cust.getCustomerId() == customerDetails.getCustomerId()) {
			return customerDao.save(cust) ;
		}
		else {
			throw new CustomerException("Can't change UserID!") ;
		}
		
	}

	@Override
	public Customer removeCustomer(Customer cust, String key) throws CustomerException, LoginException {
		
		Customer currentCustomer = currentUserSessionService.getCustomerDetails(key);
		
		if(currentCustomer != null) {
			
			if(cust.getCustomerId() == currentCustomer.getCustomerId()) {
				
				customerDao.delete(cust);
				
				Optional<CurrentUserSession> opt = currentUserSessionDao.findByUuid(key) ;
				
				CurrentUserSession currentSession = opt.get();
				
				currentUserSessionDao.delete(currentSession);
				return cust;
				
				
			}
			else {
				throw new CustomerException("Invalid Customer ID") ;
			}
			
		}
		else {
			throw new CustomerException("Invalid UUID key");
		}
	
}

	@Override
	public Customer viewCustomer(Integer customerId,String key) throws CustomerException,LoginException {
		// TODO Auto-generated method stub
		
		Customer currentCustomer = currentUserSessionService.getCustomerDetails(key);
		
		if(currentCustomer==null) {
			throw new CustomerException("Invalid UUID key") ;
		}
		
		Optional<Customer> customer = customerDao.findById(customerId);
		if(!customer.isPresent()) {
			throw new CustomerException("Invalid Customer ID") ;
		}
		
		return customer.get();
	}

	
}