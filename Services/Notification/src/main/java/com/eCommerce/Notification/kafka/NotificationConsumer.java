package com.eCommerce.Notification.kafka;
import com.eCommerce.Notification.email.EmailService;
import com.eCommerce.Notification.kafka.order.OrderConfirmation;
import com.eCommerce.Notification.kafka.payment.PaymentConfirmation;
import com.eCommerce.Notification.notification.Notification;
import com.eCommerce.Notification.notification.NotificationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
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
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic", groupId = "payment-consumer")
    public void consumePaymentNotification(String paymentConfirmation) throws MessagingException, JsonProcessingException {
        log.info(format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        ObjectMapper objectMapper = new ObjectMapper();
        PaymentConfirmation test= objectMapper.readValue(paymentConfirmation, PaymentConfirmation.class);

        repository.save(Notification.builder()
                .notificationType(PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(test)
                .build());
        var customerName= test.customerFirstname()+" "+test.customerLastname();
        emailService.sendPaymentSuccessEmail(
                test.customerEmail(),
                customerName,
                test.amount(),
                test.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic", groupId = "order-consumer")
    public void consumeOrderNotification(String orderConfirmation) throws JsonProcessingException, MessagingException {
        log.info(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        ObjectMapper objectMapper = new ObjectMapper();
        OrderConfirmation test= objectMapper.readValue(orderConfirmation, OrderConfirmation.class);

        repository.save(
                Notification.builder()
                .notificationType(ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(test)
                .build());
        var customerName= test.customer().firstName()+" "+test.customer().lastName();
        emailService.sendOrderSuccessEmail(
                test.customer().email(),
                customerName,
                test.totalAmount(),
                test.orderReference(),
                test.products()
        );
    }
}
