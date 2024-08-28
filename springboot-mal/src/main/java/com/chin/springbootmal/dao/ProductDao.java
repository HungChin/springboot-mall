package com.chin.springbootmal.dao;

import com.chin.springbootmal.dto.product.ProductQueryParmeter;
import com.chin.springbootmal.dto.product.ProductRequest;
import com.chin.springbootmal.model.Product;

import java.util.List;

public interface ProductDao {

    /**
     * 取得產品data
     * @param productId 產品Id
     * @return 產品data
     */
    public Product getProductId(Integer productId);

    /**
     * 創建產品
     * @param productRequest 創建產品請求
     * @return Integer 產品Id
     */
    public Integer createProduct(ProductRequest productRequest);

    /**
     * 更新產品
     * @param productId 產品Id
     * @param productRequest 更新產品請求
     */
    public void updateProduct(Integer productId, ProductRequest productRequest);

    /**
     * 刪除產品
     * @param productId 產品Id
     */
    public void deleteProduct(Integer productId);

    /**
     * 查詢全部產品(條件參數查詢)
     * @param parmeter 查詢產品條件參數
     * @return List<> Product 產品data
     */
    public List<Product> getAllProduct(ProductQueryParmeter parmeter);

    /**
     * 查詢產品種類數量
     * @param parmeter 查詢產品條件參數
     * @return Integer 產品數量
     */
    public Integer getProductCount(ProductQueryParmeter parmeter);
}
