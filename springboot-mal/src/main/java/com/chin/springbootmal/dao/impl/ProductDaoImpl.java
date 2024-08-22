package com.chin.springbootmal.dao.impl;

import com.chin.springbootmal.dao.ProductDao;
import com.chin.springbootmal.dto.product.ProductQueryParmeter;
import com.chin.springbootmal.dto.product.ProductRequest;
import com.chin.springbootmal.model.Product;
import com.chin.springbootmal.rowmapper.ProductRowMapper;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        map.put("category",productRequest.getCategory().toString());
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

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "UPDATE product SET product_name = :productName,category = :category, " +
                "image_url = :imageUrl, price = :price, stock = :stock, description = :description, last_modified_date = :lastModifiedDate " +
                "WHERE product_id = :productId";
        Map <String,Object> map = new HashMap<>();
        map.put("productId",productId);
        map.put("productName",productRequest.getProductName());
        map.put("category",productRequest.getCategory().toString());
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());
        map.put("lastModifiedDate",new Date());
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void deleteProduct(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId";
        Map <String,Object> map = new HashMap<>();
        map.put("productId",productId);
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public List<Product> getAllProduct(ProductQueryParmeter parmeter) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, " +
                "description, created_date, last_modified_date FROM product WHERE 1=1";
        Map <String,Object> map = new HashMap<>();
        // 條件查詢
        sql = addSqlConnect(sql,map,parmeter);
        // 排序設定
        sql += " ORDER BY "+parmeter.getOrderByColumn()+" "+(parmeter.getSortMethod().equals("desc")?"DESC":"");

        // 分頁筆數設定
        sql += " LIMIT :limit OFFSET :offset";
        map.put("limit",parmeter.getLimit());
        map.put("offset",parmeter.getOffset());
        return namedParameterJdbcTemplate.query(sql, map,new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public Integer getProductCount(ProductQueryParmeter parmeter) {
        String sql = "SELECT COUNT(*) FROM product WHERE 1=1";
        Map <String,Object> map = new HashMap<>();
        // 條件查詢
        sql = addSqlConnect(sql,map,parmeter);
        return namedParameterJdbcTemplate.queryForObject(sql,map,Integer.class);
    }

    private String addSqlConnect(String sql,Map map,ProductQueryParmeter parmeter){
        // 條件查詢
        if (parmeter.getCategory() != null){
            String category = parmeter.getCategory().name();
            sql += " AND category = :category";
            map.put("category",category);
        }
        if(StringUtils.isNotEmpty(parmeter.getSearch())){
            String search = parmeter.getSearch();
            sql += " AND product_name LIKE :search";
            map.put("search","%" + search + "%");
        }
        return sql;
    }
}

