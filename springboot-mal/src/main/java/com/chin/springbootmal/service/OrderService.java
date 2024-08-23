package com.chin.springbootmal.service;

import com.chin.springbootmal.dto.order.CreateOrderRequest;
import com.chin.springbootmal.dto.order.CreateOrderResponse;

public interface OrderService {


    public Integer createOrder(Integer userId, CreateOrderRequest orderRequest);

    public CreateOrderResponse getOrder(Integer orderId);
}
