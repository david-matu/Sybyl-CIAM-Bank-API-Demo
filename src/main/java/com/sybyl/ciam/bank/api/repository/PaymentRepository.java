package com.sybyl.ciam.bank.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.sybyl.ciam.bank.api.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {

}
