package com.shopifyapp.service;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopifyapp.Repositery.AdminDao;
import com.shopifyapp.Repositery.CurrentAdminSessionDao;
import com.shopifyapp.Repositery.CurrentUserSessionDao;
import com.shopifyapp.Repositery.CustomerDao;
import com.shopifyapp.exceptions.AdminException;
import com.shopifyapp.exceptions.LoginException;
import com.shopifyapp.exceptions.UserException;
import com.shopifyapp.login.CurrentAdminSession;
import com.shopifyapp.login.CurrentUserSession;
import com.shopifyapp.model.Admin;
import com.shopifyapp.model.Customer;
import com.shopifyapp.model.User;

import net.bytebuddy.utility.RandomString;


@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	CustomerDao customerDao;

	@Autowired
	CurrentUserSessionDao currentUserSessionDao;

	@Autowired
	CurrentUserSessionService currentUserSessionService;
	
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	CurrentAdminSessionDao adminSessionDao;
	
	
//	@Override
//	public Admin addAdmin(Admin admin) throws AdminException, LoginException {
//		// TODO Auto-generated method stub
//		Admin admin1 = adminDao.findByAdminEmail(admin.getAdminEmail());
//		
//		if(admin1 != null) {
//			throw new AdminException("this email already in use ");
//		}
//		
//		adminDao.save(admin);
//		return admin;
//	}
//	
	
//	public Admin loginAdmin(User user) throws AdminException, LoginException {
//		// TODO Auto-generated method stub
//		Admin admin1 = adminDao.findByAdminEmail(user.getUserId());
//		
//		if(admin1 == null) {
//			throw new AdminException("Admin not found with this email id ");
//		}
//		
//		Optional<CurrentAdminSession> currentAdminOptinal =  adminSessionDao.findById(admin1.getAdminId());
//		CurrentAdminSession currentAdmin = currentAdminOptinal.get();
//		if(currentAdminOptinal.isPresent()) {
//			throw new AdminException("Admin has already logged in with userId ");
//		}
//		
//		if(admin1.getAdminEmail().equals(user.getUserId()) && admin1.getAdminPassword().equals(user.getPassword())) {
//			
//			
//			
//		}
//		return null;
//	}
	
	
	@Override
	public CurrentUserSession addUser(User user) throws UserException, LoginException {
		Optional<Customer> opt = customerDao.findByMobileNumber(user.getUserId()) ;
		
		if(opt.isEmpty()) {
			throw new UserException("User not found with Mobile number");
		}
		
		Customer currentCustomer = opt.get();
		
		Integer customerId = currentCustomer.getCustomerId();
		
		Optional<CurrentUserSession> currentUserOptional = currentUserSessionDao.findByCustomerId(customerId);
		
		if(currentUserOptional.isPresent()) {
			throw new UserException("User has already logged in with userId ");
		}
		if(currentCustomer.getMobileNumber().equals(user.getUserId()) && currentCustomer.getPassword().equals(user.getPassword())) {
			
			String key = RandomString.make(6) ;
			
			CurrentUserSession currentUserSession = new CurrentUserSession(customerId, key, LocalDateTime.now()) ;
			
			return  currentUserSessionDao.save(currentUserSession) ;
			
			
		}
		else {
			throw new UserException("Invalid UserId OR Password");
		}
	}

	@Override
	public String signOut(String key) throws UserException, LoginException {
		CurrentUserSession userSession = currentUserSessionService.getCurrentUserSession(key);
		
		if(userSession != null) {
			
			currentUserSessionDao.delete(userSession);

			
			return "Logged out...";
		}
		else {
			throw new UserException("Having some problem to logout");
		}
	}

	@Override
	public User removeUser(User user, String key) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User validateUser(User user, String key) throws UserException {

		Optional<CurrentUserSession> opt = currentUserSessionDao.findByUuid(key) ;
		
		if(opt.isEmpty()) {
			throw new UserException("Invalid Key");
		}
		
		CurrentUserSession currentUser = opt.get();
		
		Optional<Customer> currentCustomerOpt = customerDao.findById(currentUser.getCustomerId()) ;
		
		Customer currentCustomer = currentCustomerOpt.get();
		
		if(user.getUserId().equals(currentCustomer.getMobileNumber()) && user.getPassword().equals(currentCustomer.getPassword())) {
			return user;
		}
		else {
			throw new UserException("Invalid Mobile Number or Password");
		}
		
		
		
	}

	
		
	
	

	
}
