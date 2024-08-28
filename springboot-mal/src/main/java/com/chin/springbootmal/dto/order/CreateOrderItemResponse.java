package com.chin.springbootmal.dto.order;

import lombok.Data;

@Data
public class CreateOrderItemResponse {

    /** 訂單明細Id */
    private Integer orderItemId;

    /** 產品Id */
    private Integer productId;

    /** 產品名稱 */
    private String productName;

    /** 訂單產品數量 */
    private Integer quantity;

    /** 產品價格 */
    private Integer price;

    /** 訂單產品金額合計 */
    private Integer amount;

    /** 產品庫存 */
    private Integer stock;

}
