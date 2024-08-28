package com.chin.springbootmal.dto.order;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    /** 訂單產品請求 */
    @NotEmpty
    private List<BuyItem> buyItems;

}
