package com.eCommerce.Order.order;

import com.eCommerce.Order.kafka.OrderConfirmation;
import com.eCommerce.Order.kafka.OrderProducer;
import com.eCommerce.Order.customer.CustomerClient;
import com.eCommerce.Order.exception.BusinessException;
import com.eCommerce.Order.orderline.OrderLineRequest;
import com.eCommerce.Order.orderline.OrderLineService;
import com.eCommerce.Order.product.ProductClient;
import com.eCommerce.Order.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    @Transactional
    public Integer createOrder(OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()->new BusinessException("Cannot create order: No customer exists with the provided ID"));

        var purchasedProducts = productClient.purchaseProducts(request.products());

        var order = this.repository.save(mapper.toOrder(request));

        for(PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(new OrderLineRequest(null,order.getId(),purchaseRequest.productID(), purchaseRequest.quantity()));
        }

        orderProducer.sendOrderConfirmation(new OrderConfirmation(request.reference(),
                request.amount(),
                request.paymentMethod(),
                customer,
                purchasedProducts));

        return order.getId();
    }

    public List<OrderResponse> finaAllOrders(){
        return this.repository.findAll().stream().map(mapper::fromOrder).toList();
    }

    public OrderResponse findByID(Integer id){
        return this.repository.findById(id).map(mapper::fromOrder)
                .orElseThrow(()->new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }
}
