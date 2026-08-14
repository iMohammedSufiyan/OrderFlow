package com.sufiyan.order_service.event;

public enum OrderEventType {
    ORDER_PLACED,
    PAYMENT_SUCCESS,
    PAYMENT_FAILED,
    ORDER_SHIPPED,
    ORDER_DELIVERED,
    ORDER_CANCELLED
}
