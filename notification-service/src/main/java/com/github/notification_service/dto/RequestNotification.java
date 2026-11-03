package com.github.notification_service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor 
@Data 
public class RequestNotification {
    private int paymentId;
    private String userEmail;
    private int  totalPrice;  
    private  List<PaymentCreatedEvent> items; 
}
