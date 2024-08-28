package com.chin.springbootmal.dto.product;

import com.chin.springbootmal.constant.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductQueryParmeter {

    /** 產品類別 */
    private ProductCategory category;

    /** 搜尋條件 */
    private String search;

    /** 排序欄位條件 */
    private String orderByColumn;

    /** 排序方式 */
    private String sortMethod;

    /** 查訊筆數限制 */
    private Integer limit;

    /** 設定查詢筆數從第幾筆查詢 */
    private Integer offset;

}
