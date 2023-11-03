package com.sonata.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonata.dto.BasketItemDto;
import com.sonata.dto.ListDto;
import com.sonata.dto.ProductDesDto;
import com.sonata.model.Product;
import com.sonata.repo.ProductRepo;
import com.sonata.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productser;
	
	
	@Autowired
    private ProductRepo productRepo;
	
	
	private final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@GetMapping("/findAll")
	public List<Product> getAll(Product product) {
		logger.info("Fetching all products");
		return productser.getProducts();
	}
	
	
	@PostMapping("/add-product")
    public void addProduct(@RequestBody ProductDesDto productDto) {
        Product product = productser.convertDtoToEntity(productDto);
        productRepo.save(product);
    }	
	
	@GetMapping("/product-details/{pid}")
	public ProductDesDto getProductDetalis(@PathVariable int pid){
		return productser.getAllProductDetails().stream().filter(i->i.getPrdId()==pid).findFirst().get();
	}
	
	@GetMapping("/list-details")
	public List<ListDto> getProducListtDetalis(){
		return productser.getListDetails();
	
	}
	
	
		@GetMapping("/productCountPerBrand")
		public Map<String, Integer> getProductCountPerBrand() {
			List<Product> products = productser.getProducts();
			return productser.getProductCountPerBrand(products);
		}
	
		
	@GetMapping("/brand/{bname}")
		public List<Product> getProductsByBrandName(@PathVariable String bname) {
			logger.info("fetching the product with name:{}", bname);

			List<Product> product = productser.getProducts();
			if (product == null) {
				logger.warn("No product was found for this brand:{}", bname);
				return (List<Product>) ResponseEntity.notFound().build();
			}
			logger.info("Found product with bname: {}", bname);
			return productser.getProductsByBrand(product, bname);
		}
	
	

	    @GetMapping("/user/{userId}")
	    public List<BasketItemDto> getBasketItemsForUser(@PathVariable Integer userId) {
	        return productser.getBasketItemsByUserId(userId);
	    }
	   
	    


	

}
