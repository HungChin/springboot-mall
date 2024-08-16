package com.chin.springbootmal.dto;

import com.chin.springbootmal.constant.PoductCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class ProductRequest {

    @NotNull
    private String productName;
    @NotNull
    private PoductCategory category;
    @NotNull
    private String imageUrl;
    @NotNull
    private Integer price;
    @NotNull
    private Integer stock;
    private String description;

}
