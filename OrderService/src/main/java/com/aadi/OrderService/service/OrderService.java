package com.aadi.OrderService.service;

import com.aadi.OrderService.model.OrderRequest;

public interface OrderService {

    long placeOrder(OrderRequest orderRequest);
    
}
