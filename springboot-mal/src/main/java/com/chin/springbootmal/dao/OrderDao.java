package com.chin.springbootmal.dao;

import com.chin.springbootmal.dto.order.OrderItemResponse;
import com.chin.springbootmal.dto.order.QueryOrderRequest;
import com.chin.springbootmal.model.Order;
import com.chin.springbootmal.model.OrderItem;

import java.util.List;

public interface OrderDao {

    /**
     * 創建訂單
     * @param order 建立訂單資訊
     * @return 訂單Id
     */
    public Integer createOrder(Order order);

    /**
     * 創建訂單明細
     * @param orderId 訂單Id
     * @param orderItem 建立訂單明細資訊
     */
    public void createOrderItems(Integer orderId,List<OrderItem> orderItem);

    /**
     * 透過訂單Id取得訂單data
     * @param orderId 訂單Id
     * @return Order 訂單data
     */
    public Order getOrderByOrderId(Integer orderId);

    /**
     * 透過訂單Id取得訂單明細data
     * @param orderId 訂單Id
     * @return List<>CreateOrderItemResponse 訂單明細data
     */
    public List<OrderItemResponse> getOrderItemByOrderId(Integer orderId);

    /**
     * 更新產品庫存數量
     * @param upStock 更新庫儲數量
     * @param productId 產品Id
     */
    public void updateProductStock(int upStock, int productId);

    /**
     * 查詢使用者底下的訂單及明細資料
     * @param orderRequest 查詢訂單條件請求
     * @return List<> Order 訂單資料
     */
    public List<Order> getOrders(QueryOrderRequest orderRequest);

    /**
     * 查詢使用者底下的訂單總數
     * @param orderRequest 查詢訂單條件請求
     * @return Integer 訂單總數量
     */
    public Integer getOrdersCount(QueryOrderRequest orderRequest);
}
