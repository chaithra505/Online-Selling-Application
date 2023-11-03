package com.sonata.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonata.dto.BasketItemDto;
import com.sonata.dto.ListDto;
import com.sonata.dto.ProductDesDto;
import com.sonata.model.BasketItem;
import com.sonata.model.Brand;
import com.sonata.model.Category;
import com.sonata.model.InventoryAndPricing;
import com.sonata.model.Product;
import com.sonata.repo.BasketRepo;
import com.sonata.repo.InventoryRepo;
import com.sonata.repo.ProductRepo;
import com.sonata.repo.UserRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productrepo;
	
	@Autowired
	private InventoryRepo inventoryrepo;
	
	@Autowired
	private BasketRepo basketrepo;
	

    @Autowired
    private UserRepo userRepo;
	
	

	public List<Product> getProducts() {
	  return productrepo.findAll();
	
	}
	

	
	public List<ProductDesDto> getAllProductDetails(){
		return productrepo.findAll()
				.stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
		
	}
		
	public List<ListDto> getListDetails() {
	    List<Object[]> result = productrepo.getListDetails();
	    List<ListDto> listDto = new ArrayList<>();

	    for (Object[] objArray : result) {
	        ListDto dto = new ListDto();
	        dto.setName((String) objArray[0]);
	        dto.setPrice((Double) objArray[1]);
	        listDto.add(dto);
	    }

	    return listDto;
	}
	
	

		
	
		
	public ProductDesDto convertEntityToDto(Product product) {
		
	    ProductDesDto productDto =new ProductDesDto();
		productDto.setPrdId(product.getPrdId());
		productDto.setName(product.getName());
		productDto.setColor(product.getColor());
		productDto.setSize(product.getSize());
		productDto.setBrandName(product.getBrand().getBrandName());
		productDto.setCategoryName(product.getCategory().getCategoryName());
		List<InventoryAndPricing> highestQuantity = inventoryrepo.getHighestQuantity(product.getPrdId());
		    
		    if (!highestQuantity.isEmpty()) {
		        InventoryAndPricing highest = highestQuantity.get(0);
		        productDto.setQuantity(highest.getQuantity());
		        productDto.setPrice(highest.getPrice());
		    } else {
		        productDto.setQuantity(0);
		        productDto.setPrice(0.0);
		    }
		    
		    return productDto;
		}

	
	
	
	
	public Product convertDtoToEntity(ProductDesDto productDto) {
	    Product product = new Product();
	    
	    product.setPrdId(productDto.getPrdId());
	    product.setName(productDto.getName());
	    product.setColor(productDto.getColor());
	    product.setSize(productDto.getSize());
	    
	    Brand brand = new Brand();
	    brand.setBrandName(productDto.getBrandName());
	    product.setBrand(brand);
	    
	    Category category = new Category();
	    category.setCategoryName(productDto.getCategoryName());
	    product.setCategory(category);
	    
	    InventoryAndPricing inventoryAndPricing = new InventoryAndPricing();
	    inventoryAndPricing.setQuantity(productDto.getQuantity());
	    inventoryAndPricing.setPrice(productDto.getPrice());
	    
	    return product;
	}


	
	
	
	public List<Product> getProductsByBrand(List<Product> products, String bname) {
		List<Product> matchingProducts = new ArrayList<>();

		for (Product product : products) {
			Brand brands = product.getBrand();
			if (brands != null && brands.getBrandName().equals(bname)) {
				matchingProducts.add(product);
			}
		}

		return matchingProducts;
	}	
	
	
	
	public Map<String, Integer> getProductCountPerBrand(List<Product> products) {
		Map<String, Integer> brandProductCount = new HashMap<>();

		for (Product product : products) {
			Brand brand = product.getBrand();
			if (brand != null) {
				String brandName = brand.getBrandName();
				brandProductCount.put(brandName, brandProductCount.getOrDefault(brandName, 0) + 1);
			}
		}

		return brandProductCount;
	}
	
	
	
	//add to bag
	
	public List<BasketItemDto> getBasketItemsByUserId(Integer userId) {
        List<BasketItem> basketItems = basketrepo.findByUser(userRepo.findById(userId).orElse(null));
        List<BasketItemDto> basketItemDTOs = new ArrayList<>();

        for (BasketItem basketItem : basketItems) {
        	BasketItemDto dto = new BasketItemDto();
          //  dto.setUserId(basketItem.getUser().getUser_id());
            dto.setPid(basketItem.getProduct().getPrdId());
            dto.setPname(basketItem.getProduct().getName());
            dto.setPrice(basketItem.getInventoryAndPricing().getPrice());
            dto.setQuantity(basketItem.getInventoryAndPricing().getQuantity());
            basketItemDTOs.add(dto);
        }

        return basketItemDTOs;
    }
	
	
	

}
