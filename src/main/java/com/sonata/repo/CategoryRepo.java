package com.sonata.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonata.model.Category;



public interface CategoryRepo  extends JpaRepository<Category, Integer> {

	Category findByCategoryName(String categoryName);


}
