package com.shopifyapp.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopifyapp.exceptions.AdminException;
import com.shopifyapp.model.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin,Integer> {

	@Query("select a from Admin a where a.adminEmail=?1")
	public Admin findByAdminEmail(String adminEmail) throws AdminException;
}
