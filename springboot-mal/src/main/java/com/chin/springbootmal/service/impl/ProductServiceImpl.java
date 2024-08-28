package com.chin.springbootmal.service.impl;

import com.chin.springbootmal.dao.ProductDao;
import com.chin.springbootmal.dto.product.ProductQueryParmeter;
import com.chin.springbootmal.dto.product.ProductRequest;
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

    /**
     * 取得產品資料
     * @param productId 產品Id Integer
     * @return Product
     */
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

    /**
     * 新增產品資料
     * @param productRequest 新增產品資訊 ProductRequest
     * @return Integer
     */
    @Override
    public Integer createProduct(ProductRequest productRequest) {
        LOG.info("執行productDao.createProduct");
        return productDao.createProduct(productRequest);
    }

    /**
     * 更新產品資料
     * @param productId 產品Id Integer
     * @param productRequest 更新產品資訊 ProductRequest
     */
    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        LOG.info("執行productDao.updateProduct");
        productDao.updateProduct(productId, productRequest);
    }

    /**
     * 刪除產品資料
     * @param productId 產品Id Integer
     */
    @Override
    public void deleteProduct(Integer productId) {
        LOG.info("執行productDao.deleteProduct");
        productDao.deleteProduct(productId);
    }

    /**
     * 查詢全部產品資料
     * @param parmeter  查詢產品挑檔條件參數 ProductQueryParmeter
     * @return List<Product> product data
     */
    @Override
    public List<Product> getAllProduct(ProductQueryParmeter parmeter) {
        LOG.info("執行productDao.getAllProduct");
        return productDao.getAllProduct(parmeter);
    }

    /**
     * 查詢產品資料數量
     * @param parmeter  查詢產品挑檔條件參數 ProductQueryParmeter
     * @return Integer 總筆數
     */
    @Override
    public Integer getProductCount(ProductQueryParmeter parmeter) {
        LOG.info("執行productDao.getProductCount");
        return productDao.getProductCount(parmeter);
    }
}
