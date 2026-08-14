package com.sufiyan.order_service.service.impl;

import com.sufiyan.order_service.dto.request.OrderRequestDto;
import com.sufiyan.order_service.dto.response.OrderResponseDto;
import com.sufiyan.order_service.event.OrderEvent;
import com.sufiyan.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Value("${kafka.topic.order-events}")
    private String orderEventsTopic;

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequest) {

        var orderEvent = OrderEvent.of(
                orderRequest.productId(),
                orderRequest.quantity(),
                orderRequest.price()
        );

        log.info("Placing order: {}", orderEvent);

        kafkaTemplate.send(orderEventsTopic, orderEvent.orderId(), orderEvent)
                .whenComplete((result, exception) -> {
                    if (exception == null) {
                        log.info("Order sent | OrderId:{} Partition:{} Offset:{}",
                                orderEvent.orderId(),
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                        // LATER: we'll update DB status to CONFIRMED here
                    } else {
                        log.error("Failed to send order {}; {}",
                                orderEvent.orderId(), exception.getMessage());
                        // LATER: we'll update DB status to FAILED here, trigger alert
                    }
                });

        return new OrderResponseDto(
                orderEvent.orderId(),
                orderEvent.productId(),
                orderEvent.quantity(),
                orderEvent.price(),
                orderEvent.status(),
                orderEvent.createdAt()
        );
    }
}
