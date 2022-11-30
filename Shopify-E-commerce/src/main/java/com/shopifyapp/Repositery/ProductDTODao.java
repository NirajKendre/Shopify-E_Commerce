package com.shopifyapp.Repositery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopifyapp.model.ProductDTO;

@Repository
public interface ProductDTODao extends JpaRepository<ProductDTO, Integer> {

	
	
}
