package com.sufiyan.order_service.controller;

import com.sufiyan.order_service.dto.request.OrderRequestDto;
import com.sufiyan.order_service.dto.response.GenericResponse;
import com.sufiyan.order_service.dto.response.OrderResponseDto;
import com.sufiyan.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public GenericResponse<OrderResponseDto> placeOrder(@RequestBody OrderRequestDto orderRequest) {
        return GenericResponse.success(
                "Order placed successfully",
                orderService.placeOrder(orderRequest)
        );
    }
}
