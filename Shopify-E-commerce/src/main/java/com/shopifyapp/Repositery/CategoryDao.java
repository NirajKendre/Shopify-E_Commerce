package com.shopifyapp.Repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopifyapp.model.Category;
import com.shopifyapp.model.Product;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {
	public Category findByCategory(String category);
}
