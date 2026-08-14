package com.sufiyan.order_service.dto.request;

public record OrderRequestDto(String productId, Integer quantity, Double price) {
}
