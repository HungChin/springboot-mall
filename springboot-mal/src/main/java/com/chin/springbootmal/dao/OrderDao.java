package com.chin.springbootmal.dao;

import com.chin.springbootmal.dto.order.CreateOrderItemResponse;
import com.chin.springbootmal.model.Order;
import com.chin.springbootmal.model.OrderItem;

import java.util.List;

public interface OrderDao {

    public Integer createOrder(Order order);

    public void createOrderItems(Integer orderId,List<OrderItem> orderItem);

    public Order getOrderByOrderId(Integer orderId);

    public List<CreateOrderItemResponse> getOrderItemByOrderId(Integer orderId);
}
