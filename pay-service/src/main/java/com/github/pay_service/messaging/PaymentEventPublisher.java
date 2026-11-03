package com.github.pay_service.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pay_service.event.PaymentCreatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Slf4j 
@Component 
@RequiredArgsConstructor 
public class PaymentEventPublisher {

    private final SqsClient sqs;
    private final ObjectMapper mapper;

    @Value("${app.sqs.payments-queue-url:}")
    private String queueUrl;

     @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onPaymentCreated(PaymentCreatedEvent event) {
        if (queueUrl.isBlank()) {
            log.warn("PAYMENTS_QUEUE_URL no configurada, no se publica el pago {}", event.paymentId());
            return;
        }
        try {
            String json = mapper.writeValueAsString(event);
            sqs.sendMessage(SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(json)
                    .build());
            log.info("Pago {} publicado en SQS", event.paymentId());
        } catch (Exception e) {
            log.error("No se pudo publicar el pago {}", event.paymentId(), e);
        }
    }
}
