package com.chin.springbootmal.service;

import com.chin.springbootmal.dto.order.CreateOrderRequest;
import com.chin.springbootmal.dto.order.OrderResponse;
import com.chin.springbootmal.dto.order.QueryOrderRequest;

import java.util.List;

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
    public OrderResponse getOrderByOrderId(Integer orderId);

    /**
     * 查詢使用者底下的訂單及明細資料
     * @param orderRequest 查詢訂單條件請求
     * @return List<> OrderResponse
     */
    public List<OrderResponse> getOrders(QueryOrderRequest orderRequest);

    /**
     * 查詢使用者底下的訂單總數
     * @param orderRequest 查詢訂單條件請求
     * @return Integer 訂單總數量
     */
    public Integer getOrdersCount(QueryOrderRequest orderRequest);
}
