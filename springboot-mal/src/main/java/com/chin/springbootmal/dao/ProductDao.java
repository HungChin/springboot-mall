package com.chin.springbootmal.dao;

import com.chin.springbootmal.dto.ProductRequest;
import com.chin.springbootmal.model.Product;

public interface ProductDao {

    public Product getProductId(Integer productId);

    public Integer createProduct(ProductRequest productRequest);
}
