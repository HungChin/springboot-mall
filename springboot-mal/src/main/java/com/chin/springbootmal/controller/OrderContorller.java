package com.chin.springbootmal.controller;


import com.chin.springbootmal.dto.order.CreateOrderRequest;
import com.chin.springbootmal.dto.order.CreateOrderResponse;
import com.chin.springbootmal.model.Order;
import com.chin.springbootmal.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderContorller {

    @Autowired
    private OrderService orderService;

    @PostMapping(value ="/user/{userId}/order")
    public ResponseEntity<CreateOrderResponse> orderCreate(@PathVariable Integer userId, @RequestBody @Valid CreateOrderRequest orderRequest) {
        try{
            Integer orderId = orderService.createOrder(userId,orderRequest);
            CreateOrderResponse createOrderResponse = orderService.getOrder(orderId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createOrderResponse);
        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
