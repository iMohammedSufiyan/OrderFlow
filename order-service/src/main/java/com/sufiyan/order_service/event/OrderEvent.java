package com.sufiyan.order_service.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderEvent(
        String orderId,
        String productId,
        Integer quantity,
        Double price,
        String status,
        LocalDateTime createdAt
) {
    public OrderEvent {
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be greater than 0");

        if (price <= 0)
            throw new IllegalArgumentException("Price must be greater than 0");
    }

    public static OrderEvent of(String productId, Integer quantity, Double price) {
        return new OrderEvent(
                UUID.randomUUID().toString(),
                productId,
                quantity,
                price,
                "PLACED",
                LocalDateTime.now()
        );
    }
}
