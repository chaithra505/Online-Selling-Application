package com.sonata.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sonata.model.BasketItem;
import com.sonata.model.User;



@Repository
public interface BasketRepo extends JpaRepository<BasketItem,Integer>  {
	List<BasketItem>  findByUser(User user);

}
