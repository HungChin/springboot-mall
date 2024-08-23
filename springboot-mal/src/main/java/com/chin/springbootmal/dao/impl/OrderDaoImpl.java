package com.chin.springbootmal.dao.impl;

import com.chin.springbootmal.dao.OrderDao;
import com.chin.springbootmal.dto.order.CreateOrderItemResponse;
import com.chin.springbootmal.model.Order;
import com.chin.springbootmal.model.OrderItem;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createOrder(Order order) {
        String sql = "INSERT INTO `order`(user_id, total_amount, created_date, last_modified_date) " +
                "VALUES (:userId, :totalAmount, :createdDate, :lastModifiedDate)";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", order.getUserId());
        params.put("totalAmount", order.getTotalAmount());
        Date now = new Date();
        params.put("createdDate", now);
        params.put("lastModifiedDate", now);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(params), keyHolder);
        Integer orderId = keyHolder.getKey().intValue();
        return orderId;
    }

    @Override
    public void createOrderItems(Integer orderId,List<OrderItem> orderItem) {
        String sql = "INSERT INTO order_item(order_id, product_id, quantity, amount) " +
                "VALUES (:orderId, :productId, :quantity, :amount)";
        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItem.size()];
        for (int i = 0; i < orderItem.size(); i++) {
            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("orderId", orderId);
            parameterSources[i].addValue("productId", orderItem.get(i).getProductId());
            parameterSources[i].addValue("quantity", orderItem.get(i).getQuantity());
            parameterSources[i].addValue("amount", orderItem.get(i).getAmount());
        }
        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
    }

    @Override
    public Order getOrderByOrderId(Integer orderId) {
        String sql = "SELECT * FROM `order` WHERE `order_id` = :orderId";
        Map<String,Object> map =  new HashMap();
        map.put("orderId", orderId);
        List<Order> order = namedParameterJdbcTemplate.query(sql, map, new BeanPropertyRowMapper<Order>(Order.class));
        return order.stream().findFirst().orElse(null);
    }

    @Override
    public List<CreateOrderItemResponse> getOrderItemByOrderId(Integer orderId) {
        return List.of();
    }
}
