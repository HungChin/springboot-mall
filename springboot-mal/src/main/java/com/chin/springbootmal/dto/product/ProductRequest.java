package com.chin.springbootmal.dto.product;

import com.chin.springbootmal.constant.ProductCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

    /** 產品名稱 */
    @NotNull
    private String productName;

    /** 產品類別 */
    @NotNull
    private ProductCategory category;

    /** 產品圖檔URL */
    @NotNull
    private String imageUrl;

    /** 產品價格 */
    @NotNull
    private Integer price;

    /** 產品庫存 */
    @NotNull
    private Integer stock;

    /** 產品描述 */
    private String description;

}
