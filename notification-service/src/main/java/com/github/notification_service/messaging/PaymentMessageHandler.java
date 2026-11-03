package com.github.notification_service.messaging;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.lambda.runtime.events.SQSBatchResponse;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.github.notification_service.dto.RequestNotification;
import com.github.notification_service.service.MailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@Slf4j 
@Configuration 
@RequiredArgsConstructor 
public class PaymentMessageHandler {

    private final MailService mailService;
    private final ObjectMapper objectMapper;

    @Bean 
    public Function<SQSEvent, SQSBatchResponse> procesar(){
        return event -> {
            List<SQSBatchResponse.BatchItemFailure> fallos = new ArrayList<>();
            
            for(SQSEvent.SQSMessage msg : event.getRecords()){
                try {
                    RequestNotification pago = objectMapper.readValue(msg.getBody(),RequestNotification.class);
                    mailService.sendBuyNotification(pago);
                } catch (Exception e) {
                    log.error("Fallo el mensaje: ", msg.getMessageId(),e);
                    fallos.add(new SQSBatchResponse.BatchItemFailure(msg.getMessageId()));
                }
            }
            return new SQSBatchResponse(fallos);
        };
    }



}
