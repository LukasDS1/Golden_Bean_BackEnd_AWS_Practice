package com.github.pay_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.pay_service.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
