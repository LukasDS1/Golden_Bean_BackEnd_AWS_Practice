package com.github.pay_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pay_service.dto.PaymentRequest;
import com.github.pay_service.model.Payment;
import com.github.pay_service.model.PaymentItem;
import com.github.pay_service.repository.PaymentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class PayService {
    
    private final PaymentRepository paymentRepository;

    public Payment createPayment(PaymentRequest request) {

        Payment payment = new Payment();
        payment.setUserEmail(request.getUserEmail());

        int total = request.getItems().stream()
            .mapToInt(i -> i.getPriceCLP() * i.getQuantity())
            .sum();

        payment.setTotalPrice(total);

        List<PaymentItem> items = request.getItems().stream()
            .map(i -> {
                PaymentItem it = new PaymentItem();
                it.setCoffeeId(i.getCoffeeId());
                it.setCoffeeName(i.getCoffeeName());
                it.setQuantity(i.getQuantity());
                it.setPriceCLP(i.getPriceCLP());
                it.setPayment(payment);
                return it;
            }).toList();

        payment.setItems(items);

        return paymentRepository.save(payment);
    }

}
