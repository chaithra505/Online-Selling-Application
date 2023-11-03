package com.sonata.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sonata.model.InventoryAndPricing;

@Repository
public interface InventoryRepo extends JpaRepository<InventoryAndPricing, Integer>{
	
	 InventoryAndPricing  findByQuantity(int quantity);
	 InventoryAndPricing  findByPrice(double price);
	 List<InventoryAndPricing>  findAllByPid(int pid);
	 
	 
	
	 @Query("SELECT i " +
		       "FROM InventoryAndPricing i " +
		       "WHERE i.pid = :pid " +
		       "ORDER BY i.quantity DESC, i.price DESC " +
		       "LIMIT 1")
	public List<InventoryAndPricing> getHighestQuantity(@Param("pid") int pid);
	

	 
	


}
