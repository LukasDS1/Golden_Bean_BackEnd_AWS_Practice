package com.github.pay_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pay_service.dto.PaymentRequest;
import com.github.pay_service.dto.ResponseDto;
import com.github.pay_service.model.Payment;
import com.github.pay_service.service.PayService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api-v1/pay")
@RequiredArgsConstructor
public class PayController {

    private final PayService payService;

    @PostMapping
    public ResponseEntity<ResponseDto<Payment>> createPayment(@RequestBody PaymentRequest request) {
        Payment saved = payService.createPayment(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            new ResponseDto<>(
                "Payment stored successfully",
                HttpStatus.CREATED.value(),
                saved,
                1
            )
        );
    }
}
