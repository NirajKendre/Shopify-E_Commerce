package com.shopifyapp.service;

import com.shopifyapp.exceptions.LoginException;
import com.shopifyapp.login.CurrentUserSession;
import com.shopifyapp.model.Customer;

public interface CurrentUserSessionService {

	public CurrentUserSession getCurrentUserSession(String key) throws LoginException;
	
	public Integer getCurrentUserCustomerId(String key) throws LoginException;
	
	public Customer getCustomerDetails(String key) throws LoginException;
	
}
