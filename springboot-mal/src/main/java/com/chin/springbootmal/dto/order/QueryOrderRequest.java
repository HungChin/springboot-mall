package com.chin.springbootmal.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueryOrderRequest {

    /** 使用者Id */
    private Integer userId;

    /** 查詢筆數限制 */
    private Integer limit;

    /** 設定從第幾筆開始查詢 */
    private Integer offSet;

}
