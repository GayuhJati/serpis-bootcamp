package com.techno.bootcamp.paymentservice.repository;

import com.techno.bootcamp.paymentservice.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
