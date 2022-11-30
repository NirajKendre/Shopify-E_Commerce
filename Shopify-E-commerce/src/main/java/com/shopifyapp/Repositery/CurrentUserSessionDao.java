package com.shopifyapp.Repositery;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopifyapp.login.CurrentUserSession;
@Repository
public interface CurrentUserSessionDao extends JpaRepository<CurrentUserSession, Integer>  {

	
	public Optional<CurrentUserSession> findByCustomerId(Integer customerId) ;
	
	public Optional<CurrentUserSession> findByUuid(String uuid) ;
	
}
