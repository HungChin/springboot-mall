package com.chin.springbootmal.service.impl;

import com.chin.springbootmal.dao.OrderDao;
import com.chin.springbootmal.dao.ProductDao;
import com.chin.springbootmal.dto.order.BuyItem;
import com.chin.springbootmal.dto.order.CreateOrderItemResponse;
import com.chin.springbootmal.dto.order.CreateOrderRequest;
import com.chin.springbootmal.dto.order.CreateOrderResponse;
import com.chin.springbootmal.model.Order;
import com.chin.springbootmal.model.OrderItem;
import com.chin.springbootmal.model.Product;
import com.chin.springbootmal.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Override
    @Transactional
    public Integer createOrder(Integer userId, CreateOrderRequest orderRequest) {
        List<BuyItem> buyItemList = orderRequest.getBuyItems();
        List<OrderItem> orderItems = new ArrayList<>();
        int total = 0;
        for (BuyItem buyItem : buyItemList) {
            int productId  = buyItem.getProductId();
            Product product = productDao.getProductId(productId);
            int quantity = buyItem.getQuantity();
            int price = product.getPrice();
            int amount = quantity * price;
            total += amount;
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(productId);
            orderItem.setQuantity(quantity);
            orderItem.setAmount(amount);
            orderItems.add(orderItem);
        }
        Order order = new Order();
        order.setTotalAmount(total);
        order.setUserId(userId);
        LOG.info("執行createDao.createOrder");
        Integer orderId  = orderDao.createOrder(order);
        LOG.info("執行createDao.createOrder");
        orderDao.createOrderItems(orderId,orderItems);
        return orderId;
    }

    @Override
    public CreateOrderResponse getOrder(Integer orderId) {
        Order order = orderDao.getOrderByOrderId(orderId);
        List<CreateOrderItemResponse> orderItemResponses = orderDao.getOrderItemByOrderId(orderId);
        return new CreateOrderResponse(order,orderItemResponses);
    }
}
