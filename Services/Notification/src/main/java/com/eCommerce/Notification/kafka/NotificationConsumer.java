package com.eCommerce.Notification.kafka;

import com.eCommerce.Notification.email.EmailService;
import com.eCommerce.Notification.kafka.order.OrderConfirmation;
import com.eCommerce.Notification.kafka.payment.PaymentConfirmation;
import com.eCommerce.Notification.notification.Notification;
import com.eCommerce.Notification.notification.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.eCommerce.Notification.notification.NotificationType.ORDER_CONFIRMATION;
import static com.eCommerce.Notification.notification.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository repository;
    private final EmailService service;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentNotification(PaymentConfirmation paymentConfirmation){
        log.info(format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        repository.save(Notification.builder()
                .notificationType(PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build());
        var customerName= paymentConfirmation.customerFirstname()+" "+paymentConfirmation.customerLastname();
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderNotification(OrderConfirmation orderConfirmation){
        log.info(format("Consuming the message from payment-topic Topic:: %s", orderConfirmation));
        repository.save(Notification.builder()
                .notificationType(ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build());
        var customerName= orderConfirmation.customer().firstname()+" "+orderConfirmation.customer().lastname();
    }
}
