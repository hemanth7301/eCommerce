package com.eCommerce.Payment.payment;

import com.eCommerce.Payment.kafka.NotificationProducer;
import com.eCommerce.Payment.kafka.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer producer;

    public Integer createPayment(PaymentRequest request){
        var payment= this.repository.save(mapper.toPayment(request));
        producer.sendNotification(new PaymentNotificationRequest(
                request.orderReference(),
                request.amount(),
                request.paymentMethod(),
                request.customer().firstName(),
                request.customer().lastName(),
                request.customer().email()
        ));
        return payment.getId();
    }
}
