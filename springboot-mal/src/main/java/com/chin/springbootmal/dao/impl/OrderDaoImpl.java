package com.chin.springbootmal.dao.impl;

import com.chin.springbootmal.dao.OrderDao;
import com.chin.springbootmal.dto.order.OrderItemResponse;
import com.chin.springbootmal.dto.order.QueryOrderRequest;
import com.chin.springbootmal.model.Order;
import com.chin.springbootmal.model.OrderItem;
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

    /**
     * 創建訂單
     * @param order 建立訂單資訊
     * @return 訂單Id
     */
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

    /**
     * 創建訂單明細
     * @param orderId 訂單Id
     * @param orderItem 建立訂單明細資訊
     */
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

    /**
     * 透過訂單Id取得訂單data
     * @param orderId 訂單Id
     * @return Order 訂單data
     */
    @Override
    public Order getOrderByOrderId(Integer orderId) {
        String sql = "SELECT * FROM `order` WHERE `order_id` = :orderId";
        Map<String,Object> map =  new HashMap();
        map.put("orderId", orderId);
        List<Order> order = namedParameterJdbcTemplate.query(sql, map, new BeanPropertyRowMapper<Order>(Order.class));
        return order.stream().findFirst().orElse(null);
    }

    /**
     * 透過訂單Id取得訂單明細data
     * @param orderId 訂單Id
     * @return List<>CreateOrderItemResponse 訂單明細data
     */
    @Override
    public List<OrderItemResponse> getOrderItemByOrderId(Integer orderId) {
        String sql = "SELECT itm.order_item_id,itm.product_id,pdt.product_name,itm.quantity,pdt.price,itm.amount,pdt.stock FROM order_item AS itm " +
                "LEFT JOIN product AS pdt ON itm.product_id = pdt.product_id WHERE itm.order_id = :orderId";
        Map<String,Object> map = new HashMap<>();
        map.put("orderId",orderId);
        return namedParameterJdbcTemplate.query(sql,map,new BeanPropertyRowMapper<>(OrderItemResponse.class));
    }

    /**
     *
     * @param upStock 更新產品庫儲數量
     * @param productId 產品Id
     */
    @Override
    public void updateProductStock(int upStock, int productId) {
        String sql = "UPDATE product SET stock = :upStock,last_modified_date = :lastModifiedDate WHERE product_id = :productId";
        Map<String,Object> map = new HashMap<>();
        map.put("upStock",upStock);
        Date now = new Date();
        map.put("lastModifiedDate",now);
        map.put("productId",productId);
        namedParameterJdbcTemplate.update(sql,map);
    }

    /**
     * 查詢使用者底下的訂單及明細資料
     * @param orderRequest 查詢訂單條件請求
     * @return List<> Order 訂單資料
     */
    @Override
    public List<Order> getOrders(QueryOrderRequest orderRequest) {
        String sql = "SELECT * FROM `order` WHERE 1=1";
        Map<String,Object> map = new HashMap<>();
        sql = addSqlConnect(sql,orderRequest,map);
        sql += " ORDER BY created_date DESC";
        sql += " LIMIT :limit OFFSET :offSet";
        map.put("limit",orderRequest.getLimit());
        map.put("offSet",orderRequest.getOffSet());
        return namedParameterJdbcTemplate.query(sql,map,new BeanPropertyRowMapper<>(Order.class));
    }

    /**
     * 查詢使用者底下的訂單總數
     * @param orderRequest 查詢訂單條件請求
     * @return Integer 訂單總數量
     */
    @Override
    public Integer getOrdersCount(QueryOrderRequest orderRequest) {
        String sql = "SELECT COUNT(*) FROM `order` WHERE 1=1";
        Map<String,Object> map = new HashMap<>();
        sql = addSqlConnect(sql,orderRequest,map);
        return namedParameterJdbcTemplate.queryForObject(sql,map, Integer.class);
    }

    /**
     * 串接查詢SQL語句
     * @param sql SQL語句
     * @param orderRequest 訂單條件請求
     * @param map 查詢條件map
     * @return String 回傳查詢sql 串接字串
     */
    private String addSqlConnect(String sql,QueryOrderRequest orderRequest,Map<String,Object> map){
        Integer userId = orderRequest.getUserId();
        if (userId != null){
            sql += " AND user_id = :userId";
            map.put("userId",userId);
        }
        return sql;
    }

}
