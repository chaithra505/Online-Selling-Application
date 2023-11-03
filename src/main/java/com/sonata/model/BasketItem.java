package com.sonata.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "basket_item")
public class BasketItem {
    @Id
    @GeneratedValue
    private int bkt_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pid")
    private Product product;
    
    @OneToOne
    @JoinColumn(name = "batchId")
    private InventoryAndPricing inventoryAndPricing;

 
   
	public int getBkt_id() {
		return bkt_id;
	}
	public void setBkt_id(int bkt_id) {
		this.bkt_id = bkt_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public InventoryAndPricing getInventoryAndPricing() {
		return inventoryAndPricing;
	}
	public void setInventoryAndPricing(InventoryAndPricing inventoryAndPricing) {
		this.inventoryAndPricing = inventoryAndPricing;
	}


   
}
