package com.eCommerce.Order.orderline;

import com.eCommerce.Order.order.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest request){
        return this.repository.save(mapper.toOrderLine(request)).getId();
    }

    public List<OrderLineResponse> findAllByOrderID(Integer orderID){
        return this.repository.findAllByOrderId(orderID).stream().map(mapper::fromOrderLine).toList();
    }
}
