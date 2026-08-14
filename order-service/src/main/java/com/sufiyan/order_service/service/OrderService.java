package com.sufiyan.order_service.service;

import com.sufiyan.order_service.dto.request.OrderRequestDto;
import com.sufiyan.order_service.dto.response.OrderResponseDto;

public interface OrderService {
    OrderResponseDto placeOrder(OrderRequestDto orderRequest);
}
