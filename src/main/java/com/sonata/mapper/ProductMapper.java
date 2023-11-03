package com.sonata.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sonata.dto.ProductDesDto;



public class ProductMapper {
    
	public ProductDesDto mapResultSetToDto(ResultSet resultSet) throws SQLException {
        ProductDesDto productDto = new ProductDesDto();
        productDto.setPrdId(resultSet.getInt("prdId"));
        productDto.setName(resultSet.getString("name"));
        productDto.setColor(resultSet.getString("color"));
        productDto.setSize(resultSet.getString("size"));   
        productDto.setBrandName(resultSet.getString("brandName"));
        productDto.setCategoryName(resultSet.getString("categoryName"));
        productDto.setQuantity(resultSet.getInt("quantity"));
        productDto.setPrice(resultSet.getDouble("price"));
        return productDto;
    }   
	
	
	
}
