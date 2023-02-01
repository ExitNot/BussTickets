package com.test.ticket_service.business.service.impl;

import com.test.ticket_service.business.service.PaymentService;
import com.test.ticket_service.data.PaymentRepo;
import com.test.ticket_service.data.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;

    @Autowired
    public PaymentServiceImpl(PaymentRepo paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    @Override
    public Payment create(Payment data) {
        return paymentRepo.save(data);
    }

    @Override
    public Optional<Payment> readById(Long id) {
        return paymentRepo.findById(id);
    }

    @Override
    public Page<Payment> readAll(Pageable pageable) {
        return null;
    }

    @Override
    public void update(Long id, Payment newData) {
        paymentRepo.save(newData);
    }

    @Override
    public void delete(Long id) {}
}
