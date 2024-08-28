package com.chin.springbootmal.service.impl;

import com.chin.springbootmal.dao.OrderDao;
import com.chin.springbootmal.dao.ProductDao;
import com.chin.springbootmal.dao.UserDao;
import com.chin.springbootmal.dto.order.*;
import com.chin.springbootmal.model.Order;
import com.chin.springbootmal.model.OrderItem;
import com.chin.springbootmal.model.Product;
import com.chin.springbootmal.model.User;
import com.chin.springbootmal.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    /**
     * 建立訂單及訂定單明細
     * @param userId Integer 使用者Id
     * @param orderRequest CreateOrderRequest 創建訂單請求
     * @return Integer 回傳訂單Id
     */
    @Override
    @Transactional
    public Integer createOrder(Integer userId, CreateOrderRequest orderRequest) {
        User user = userDao.getUserByUserId(userId);
        if (user == null){
            LOG.warn("userId:{}不存在",userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<BuyItem> buyItemList = orderRequest.getBuyItems();
        List<OrderItem> orderItems = new ArrayList<>();
        int total = 0;
        for (BuyItem buyItem : buyItemList) {
            int productId  = buyItem.getProductId();
            Product product = productDao.getProductId(productId);
            int quantity = buyItem.getQuantity();
            if (product.getStock() >= quantity){
                int price = product.getPrice();
                int amount = quantity * price;
                total += amount;
                int upStock = product.getStock() - quantity;
                orderDao.updateProductStock(upStock,productId);
                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(productId);
                orderItem.setQuantity(quantity);
                orderItem.setAmount(amount);
                orderItems.add(orderItem);
            }else{
                LOG.warn("產品id:{},名稱:{}庫存不足無法建立訂單",product.getProductId(),product.getProductName());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        LOG.info("product產品數量已更新");
        Order order = new Order();
        order.setTotalAmount(total);
        order.setUserId(userId);
        LOG.info("執行createDao.createOrder 建立訂單");
        Integer orderId  = orderDao.createOrder(order);
        LOG.info("執行createDao.createOrderItems 建立訂單明細");
        orderDao.createOrderItems(orderId,orderItems);
        return orderId;
    }

    /**
     * 查詢創建後訂單及明細
     * @param orderId 訂單Id
     * @return CreateOrderResponse 創建訂單回應
     */
    @Override
    public OrderResponse getOrderByOrderId(Integer orderId) {
        Order order = orderDao.getOrderByOrderId(orderId);
        List<OrderItemResponse> orderItemResponses = orderDao.getOrderItemByOrderId(orderId);
        return new OrderResponse(order,orderItemResponses);
    }

    /**
     * 查詢使用者底下的訂單及明細資料
     * @param orderRequest 查詢訂單條件請求
     * @return List<> OrderResponse
     */
    @Override
    public List<OrderResponse> getOrders(QueryOrderRequest orderRequest) {
        List<OrderResponse> orderResponseList = new ArrayList<>();
        LOG.info("執行orderDao.getOrders 查詢使用者訂單資料");
        List<Order> orderList = orderDao.getOrders(orderRequest);
        for (Order order:orderList){
            LOG.info("執行orderDao.getOrderItemByOrderId 查詢使用者訂單明細資料");
            List<OrderItemResponse> orderItemList = orderDao.getOrderItemByOrderId(order.getOrderId());
            OrderResponse orderResponse = new OrderResponse(order,orderItemList);
            orderResponseList.add(orderResponse);
        }
        return orderResponseList;
    }

    /**
     * 查詢使用者底下的訂單總數
     * @param orderRequest 查詢訂單條件請求
     * @return Integer 訂單總數量
     */
    @Override
    public Integer getOrdersCount(QueryOrderRequest orderRequest) {
        LOG.info("執行orderDao.getOrdersCount 查詢使用者訂單總數量");
        return orderDao.getOrdersCount(orderRequest);
    }


}
