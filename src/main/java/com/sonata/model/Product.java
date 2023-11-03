package com.sonata.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prdId;
	private String name;
	private String color;
	private String size;
	private Boolean active;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	
	@ManyToOne(targetEntity=Category.class, cascade = CascadeType.ALL)
	@JoinColumn(name="fk_cat_id")
	private Category category;
	
    
	
	
	public Product() {}
	

	public Product(int prdId, double price, String color, String size, String name) {
		this.prdId = prdId;
		this.color = color;
		this.size = size;
		this.name = name;
	}


	public int getPrdId() {
		return prdId;
	}


	public void setPrdId(int prdId) {
		this.prdId = prdId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	public Brand getBrand() {
		return brand;
	}


	public void setBrand(Brand brand) {
		this.brand = brand;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	
	


}
