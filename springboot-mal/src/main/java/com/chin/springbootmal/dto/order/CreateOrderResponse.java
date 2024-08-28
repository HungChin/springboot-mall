package com.chin.springbootmal.dto.order;

import com.chin.springbootmal.model.Order;
import com.chin.springbootmal.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponse {

    /** 訂單資訊 */
    private Order order;

    /** 訂單明細資訊 */
    private List<CreateOrderItemResponse> createOrderItemResponseList;

}
