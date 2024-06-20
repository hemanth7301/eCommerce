package com.eCommerce.Payment.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationProducer {
    private final KafkaTemplate<String ,PaymentNotificationRequest> kafkaTemplate;

    public void sendNotification(PaymentNotificationRequest request){
        log.info("Sending notification with body : {}",request);
        var message= MessageBuilder.withPayload(request).setHeader("TOPIC","payment-topic").build();
        kafkaTemplate.send(message);
    }
}
