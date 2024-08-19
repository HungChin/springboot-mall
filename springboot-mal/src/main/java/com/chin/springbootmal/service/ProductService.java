package com.chin.springbootmal.service;

import com.chin.springbootmal.constant.PoductCategory;
import com.chin.springbootmal.dto.ProductQueryParmeter;
import com.chin.springbootmal.dto.ProductRequest;
import com.chin.springbootmal.model.Product;

import java.util.List;

public interface ProductService {

    public Product getProductById(Integer productId);

    public Integer createProduct(ProductRequest productRequest);

    public void updateProduct(Integer productId, ProductRequest productRequest);

    public void deleteProduct(Integer productId);

    public List<Product> getAllProduct(ProductQueryParmeter parmeter);
}
