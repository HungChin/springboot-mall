package com.chin.springbootmal.dao.impl;

import com.chin.springbootmal.dao.ProductDao;
import com.chin.springbootmal.dto.ProductRequest;
import com.chin.springbootmal.model.Product;
import com.chin.springbootmal.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Product getProductId(Integer productId) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date FROM product WHERE product_id = :productId";
        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        return  productList.stream().findFirst().orElse(null);

    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product(product_name, category, image_url, price, stock, description, created_date, last_modified_date) " +
                "VALUES(:productName,:category,:imageUrl,:price,:stock,:description,:createdDate,:lastModifiedDate)";
        Map <String,Object> map = new HashMap<>();
        map.put("productName",productRequest.getProductName());
        map.put("category",productRequest.getCategory());
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());
        map.put("createdDate",new Date());
        map.put("lastModifiedDate",new Date());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        if(result >0){
            Integer productId = keyHolder.getKey().intValue();
            return productId;
        }else{
            return null;
        }
    }
}
