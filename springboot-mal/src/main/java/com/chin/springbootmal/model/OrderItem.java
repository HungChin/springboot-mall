package com.chin.springbootmal.model;

import lombok.Data;

@Data
public class OrderItem {

    /** 訂單明細Id */
    private Integer orderItemId;

    /** 訂單Id */
    private Integer orderId;

    /** 產品Id */
    private Integer productId;

    /** 訂單產品數量 */
    private Integer quantity;

    /** 訂單產品金額合計 */
    private Integer amount;

}
