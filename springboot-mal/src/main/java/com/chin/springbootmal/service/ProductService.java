package com.chin.springbootmal.service;

import com.chin.springbootmal.dto.ProductRequest;
import com.chin.springbootmal.model.Product;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    public Product getProductById(Integer productId);

    public Integer createProduct(ProductRequest productRequest);
}
