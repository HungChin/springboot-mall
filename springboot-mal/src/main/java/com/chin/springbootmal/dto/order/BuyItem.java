package com.chin.springbootmal.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BuyItem {

    /** 產品Id */
    @NotNull
    private Integer productId;

    /** 訂單產品數量 */
    @NotNull
    private Integer quantity;
}
