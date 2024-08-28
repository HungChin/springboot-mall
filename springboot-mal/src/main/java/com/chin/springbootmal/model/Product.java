package com.chin.springbootmal.model;

import com.chin.springbootmal.constant.ProductCategory;
import lombok.Data;

import java.util.Date;

@Data
public class Product {

    /** 產品Id */
    private Integer productId;

    /** 產品使用者 */
    private String productName;

    /** 產品類別 */
    private ProductCategory category;

    /** 產品圖片URL */
    private String imageUrl;

    /** 產品價格 */
    private Integer price;

    /** 產品庫存 */
    private Integer stock;

    /** 產品描述 */
    private String description;

    /** 產品建立日期 */
    private Date createdDate;

    /** 產品最後修改日期 */
    private Date lastModifiedDate;

}
