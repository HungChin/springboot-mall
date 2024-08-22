package com.chin.springbootmal.dao;

import com.chin.springbootmal.dto.product.ProductQueryParmeter;
import com.chin.springbootmal.dto.product.ProductRequest;
import com.chin.springbootmal.model.Product;

import java.util.List;

public interface ProductDao {

    public Product getProductId(Integer productId);

    public Integer createProduct(ProductRequest productRequest);

    public void updateProduct(Integer productId, ProductRequest productRequest);

    public void deleteProduct(Integer productId);

    public List<Product> getAllProduct(ProductQueryParmeter parmeter);

    public Integer getProductCount(ProductQueryParmeter parmeter);
}
