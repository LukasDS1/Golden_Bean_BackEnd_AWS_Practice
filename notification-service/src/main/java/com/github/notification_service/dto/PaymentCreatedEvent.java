package com.github.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class PaymentCreatedEvent {
    private String coffeeName;
    private Integer quantity;
    private Integer priceCLP;
}
