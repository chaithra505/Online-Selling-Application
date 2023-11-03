package com.sonata.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sonata.dto.ProductDesDto;
import com.sonata.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

	
	@Query("SELECT new com.sonata.dto.ProductDesDto(p.name AS name, p.prdId AS prdId ,b.brandName AS brandName, c.categoryName AS categoryName) " +
		       "FROM Product p " +
		       "JOIN p.brand b " +
		       "JOIN p.category c ")
		public List<ProductDesDto> getproductDetails();

	
	
	@Query("SELECT p.name AS pname, MAX(i.price) AS maxPrice " +
		       "FROM Product p " +
		       "JOIN InventoryAndPricing i ON p.prdId = i.pid " +
		       "GROUP BY i.pid")
		public List<Object[]> getListDetails();


	  


	
	List<Product> findByName(String name);
	
	List<Product> findBySize(String size);
	
	List<Product> findByColor(String color);



	
	
	
	


}
