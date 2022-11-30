package com.shopifyapp.Repositery;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopifyapp.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

	public Optional<Customer> findByMobileNumber(String mobileNumber) ;

}
