package com.chin.springbootmal.dao;

import com.chin.springbootmal.dto.order.CreateOrderItemResponse;
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
    public List<CreateOrderItemResponse> getOrderItemByOrderId(Integer orderId);

    /**
     * 更新產品庫存數量
     * @param upStock 更新庫儲數量
     * @param productId 產品Id
     */
    public void updateProductStock(int upStock, int productId);
}
