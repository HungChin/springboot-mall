package com.chin.springbootmal.controller;


import com.chin.springbootmal.dto.order.CreateOrderRequest;
import com.chin.springbootmal.dto.order.OrderResponse;
import com.chin.springbootmal.dto.order.QueryOrderRequest;
import com.chin.springbootmal.service.OrderService;
import com.chin.springbootmal.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderContorller {

    @Autowired
    private OrderService orderService;

    /**
     * 取得使用者id的所有訂單資料
     * @param userId Integer 使用者Id
     * @param limit Integer 查詢的筆數限制(數字設定限制在0-1000)
     * @param offSet Integer 設定從第幾筆開始查詢(數字設定需大於0)
     * @return ResponseEntity<> Page<> OrderResponse
     */
    @Validated
    @GetMapping(value = "/user/{userId}/orders")
    public ResponseEntity<Page<OrderResponse>> getOrders(
            @PathVariable Integer userId,
            @RequestParam(value = "limit",defaultValue = "10") @Max(1000) @Min(0) Integer limit,
            @RequestParam(value = "offSet",defaultValue = "0") @Min(0) Integer offSet){

        QueryOrderRequest orderRequest = new QueryOrderRequest(userId,limit,offSet);
        try{
            //查詢使用者訂單資料
            List<OrderResponse> orderResponseList = orderService.getOrders(orderRequest);
            //使用者訂單數量查詢
            Integer tortal = orderService.getOrdersCount(orderRequest);
            //分頁資料設定
            Page<OrderResponse> pageData = new Page<>(limit,offSet,tortal,orderResponseList);
            return ResponseEntity.status(HttpStatus.OK).body(pageData);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    /**
     * 創建使用者訂單及明細
     * @param userId 使用者Id
     * @param orderRequest 創建訂單請求
     * @return ResponseEntity<> CreateOrderResponse
     */
    @PostMapping(value ="/user/{userId}/order")
    public ResponseEntity<OrderResponse> orderCreate(@PathVariable Integer userId, @RequestBody @Valid CreateOrderRequest orderRequest) {
        try{
            Integer orderId = orderService.createOrder(userId,orderRequest);
            OrderResponse createOrderResponse = orderService.getOrderByOrderId(orderId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createOrderResponse);
        }catch (Exception ex) {
            String message  = ex.getMessage();
            if(message.equals(HttpStatus.BAD_REQUEST.toString())){
                return   ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
