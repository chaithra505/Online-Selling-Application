package com.sonata.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import com.sonata.model.Brand;

public interface BrandRepo  extends JpaRepository<Brand, Integer> {
	 
		Brand findBybrandName(String brandName);
		
}
