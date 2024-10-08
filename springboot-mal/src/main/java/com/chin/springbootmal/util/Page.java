package com.chin.springbootmal.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Page <T>{

    /** 查詢的筆數限制 */
    private Integer limit;

    /** 設定從第幾筆開始查詢 */
    private Integer offset;

    /** 總筆數 */
    private Integer total;

    /** 分頁筆數資料 */
    private List<T> results;

}
