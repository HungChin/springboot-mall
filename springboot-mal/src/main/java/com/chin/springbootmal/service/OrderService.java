package com.chin.springbootmal.service;

import com.chin.springbootmal.dto.order.CreateOrderRequest;
import com.chin.springbootmal.dto.order.CreateOrderResponse;

public interface OrderService {

    /**
     * 建立訂單及訂定單明細
     * @param userId Integer 使用者Id
     * @param orderRequest CreateOrderRequest 創建訂單請求
     * @return Integer 回傳訂單Id
     */
    public Integer createOrder(Integer userId, CreateOrderRequest orderRequest);

    /**
     * 查詢創建後訂單及明細
     * @param orderId 訂單Id
     * @return CreateOrderResponse 創建訂單回應
     */
    public CreateOrderResponse getOrder(Integer orderId);
}
