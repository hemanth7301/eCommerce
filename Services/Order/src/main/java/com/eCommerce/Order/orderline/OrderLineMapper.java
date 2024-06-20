package com.eCommerce.Order.orderline;

import com.eCommerce.Order.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request){
        return OrderLine.builder()
                .id(request.id())
                .order(Order.builder()
                        .id(request.orderId())
                        .build())
                .productID(request.productID())
                .quantity(request.quantity())
                .build();
    }

    public OrderLineResponse fromOrderLine(OrderLine orderLine){
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
