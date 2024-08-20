package com.chin.springbootmal.dto;

import com.chin.springbootmal.constant.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductQueryParmeter {
    private ProductCategory category;
    private String search;
    private String orderByColumn;
    private String sortMethod;
    private Integer limit;
    private Integer offset;
}
