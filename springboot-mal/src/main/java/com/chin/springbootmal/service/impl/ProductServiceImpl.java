package com.chin.springbootmal.service.impl;

import com.chin.springbootmal.dao.ProductDao;
import com.chin.springbootmal.dto.ProductQueryParmeter;
import com.chin.springbootmal.dto.ProductRequest;
import com.chin.springbootmal.model.Product;
import com.chin.springbootmal.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        LOG.info("執行productDao.createProduct");
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        LOG.info("執行productDao.updateProduct");
        productDao.updateProduct(productId, productRequest);
    }

    @Override
    public void deleteProduct(Integer productId) {
        LOG.info("執行productDao.deleteProduct");
        productDao.deleteProduct(productId);
    }

    @Override
    public List<Product> getAllProduct(ProductQueryParmeter parmeter) {
        LOG.info("執行productDao.getAllProduct");
        return productDao.getAllProduct(parmeter);
    }

    @Override
    public Integer getProductCount(ProductQueryParmeter parmeter) {
        LOG.info("執行productDao.getProductCount");
        return productDao.getProductCount(parmeter);
    }
}
