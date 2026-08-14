package com.sufiyan.order_service.dto.response;

import java.time.LocalDateTime;

public record OrderResponseDto(
        String orderId,
        String productId,
        Integer quantity,
        Double price,
        String status,
        LocalDateTime createdAt
) {
}
