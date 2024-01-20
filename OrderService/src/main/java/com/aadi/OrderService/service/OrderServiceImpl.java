package com.aadi.OrderService.service;

import com.aadi.OrderService.entity.Order;
import com.aadi.OrderService.external.client.ProductServiceClient;
import com.aadi.OrderService.model.OrderRequest;
import com.aadi.OrderService.repository.OrderRepository;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  private final ProductServiceClient productServiceClient;

  @Override
  public long placeOrder(OrderRequest orderRequest) {

    // create order entity from orderRequest
    // call productService API to reduce the qty
    // paymentService -> Payment -> success -> COMPLETE else canceled

    productServiceClient.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());

    Order order =
        Order.builder()
            .amount(orderRequest.getTotalAmount())
            .quantity(orderRequest.getQuantity())
            .orderDate(Instant.now())
            .orderStatus("CREATED")
            .productId(orderRequest.getProductId())
            .build();

    log.info("Placing order " + order.toString());

    order = orderRepository.save(order);

    return order.getId();
  }
}
