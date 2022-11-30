package com.shopifyapp.service;


import com.shopifyapp.exceptions.AdminException;
import com.shopifyapp.exceptions.LoginException;
import com.shopifyapp.exceptions.UserException;
import com.shopifyapp.login.CurrentUserSession;
import com.shopifyapp.model.Admin;
import com.shopifyapp.model.User;

public interface LoginService {
	
//	public Admin addAdmin(Admin admin) throws AdminException, LoginException ;

	public CurrentUserSession addUser(User user) throws UserException, LoginException ;
	
	public User removeUser(User user,String key) throws UserException ;
	
	public User validateUser(User user,String key) throws UserException, LoginException ;
	
	public String signOut(String key) throws UserException, LoginException ;
	
	
	
}
