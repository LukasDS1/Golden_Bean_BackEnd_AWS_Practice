package com.github.pay_service.dto;

import lombok.Data;

@Data
public class PaymentItemRequest {
    private Long coffeeId;
    private String coffeeName;
    private Integer quantity;
    private Integer priceCLP;
}
