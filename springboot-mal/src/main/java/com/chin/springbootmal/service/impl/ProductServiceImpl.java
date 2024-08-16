package com.chin.springbootmal.service.impl;

import com.chin.springbootmal.dao.ProductDao;
import com.chin.springbootmal.dto.ProductRequest;
import com.chin.springbootmal.model.Product;
import com.chin.springbootmal.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductDao productDao;
    @Override
    public Product getProductById(Integer productId) {
        Product product = null;
        try{
            LOG.info("執行productDao.getProductId");
            product =  productDao.getProductId(productId);
        }catch (Exception ex){
            LOG.error("執行productDao.getProductId錯誤" );
            ex.printStackTrace();
        }
        return product;
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }
}
