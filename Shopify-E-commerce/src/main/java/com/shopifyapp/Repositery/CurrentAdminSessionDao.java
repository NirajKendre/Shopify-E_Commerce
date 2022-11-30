package com.shopifyapp.Repositery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopifyapp.login.CurrentAdminSession;

@Repository
public interface CurrentAdminSessionDao extends JpaRepository<CurrentAdminSession, Integer> {

}
