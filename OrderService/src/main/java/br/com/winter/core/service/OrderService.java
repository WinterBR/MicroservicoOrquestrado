package br.com.winter.core.service;


import br.com.winter.core.dto.OrderRequest;
import br.com.winter.core.entity.Event;
import br.com.winter.core.entity.Order;

import br.com.winter.core.producer.SagaProducer;
import br.com.winter.core.repository.IOrderRespository;
import br.com.winter.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

    private static final String TRANSACTION_ID_PATTERN = "%s_%s";

    private final EventService eventService;
    private final SagaProducer sagaProducer;
    private final JsonUtil jsonUtil;
    private final IOrderRespository iOrderRespository;

    public Order createOrder(OrderRequest orderRequest) {
        var order = Order
                .builder()
                .products(orderRequest.getProducts())
                .createdAt(LocalDateTime.now())
                .transactionId(
                        String.format("%s_%s", Instant.now().toEpochMilli(), UUID.randomUUID())
                )
                .build();
        iOrderRespository.save(order);
        sagaProducer.sendEvent(jsonUtil.toJson(createPayload(order)));
        return order;
    }

    private Event createPayload(Order order) {
        var event = Event
                .builder()
                .orderId(order.getId())
                .transactionId(order.getTransactionId())
                .payload(order)
                .createdAt(LocalDateTime.now())
                .build();
        eventService.saveEvent(event);
        return event;
    }
}
