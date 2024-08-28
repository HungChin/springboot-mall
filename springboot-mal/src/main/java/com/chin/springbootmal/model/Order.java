package com.chin.springbootmal.model;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

    /** 訂單Id */
    private Integer orderId;

    /** 使用者Id */
    private Integer userId;

    /** 訂單產品總金額合計 */
    private Integer totalAmount;

    /** 訂單建立日期 */
    private Date createdDate;

    /** 訂單最後修改日期 */
    private Date lastModifiedDate;

}
