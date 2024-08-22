package com.chin.springbootmal.service;

import com.chin.springbootmal.dto.product.ProductQueryParmeter;
import com.chin.springbootmal.dto.product.ProductRequest;
import com.chin.springbootmal.model.Product;

import java.util.List;

public interface ProductService {

    /**
     * 取得產品資料
     * @param productId 產品Id Integer
     * @return Product
     */
    public Product getProductById(Integer productId);

    /**
     * 新增產品資料
     * @param productRequest 新增產品資訊 ProductRequest
     * @return Integer
     */
    public Integer createProduct(ProductRequest productRequest);

    /**
     * 更新產品資料
     * @param productId 產品Id Integer
     * @param productRequest 更新產品資訊 ProductRequest
     */
    public void updateProduct(Integer productId, ProductRequest productRequest);

    /**
     * 刪除產品資料
     * @param productId 產品Id Integer
     */
    public void deleteProduct(Integer productId);

    /**
     * 查詢全部產品資料
     * @param parmeter  查詢產品挑檔條件參數 ProductQueryParmeter
     * @return List<Product> product data
     */
    public List<Product> getAllProduct(ProductQueryParmeter parmeter);

    /**
     * 查詢產品資料數量
     * @param parmeter  查詢產品挑檔條件參數 ProductQueryParmeter
     * @return Integer 總筆數
     */
    public Integer getProductCount(ProductQueryParmeter parmeter);
}
