package com.github.pay_service.dto;

import java.util.List;

import lombok.Data;

@Data
public class PaymentRequest {
    private String userEmail;
    private List<PaymentItemRequest> items;
}
