package com.sybyl.ciam.bank.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.sybyl.ciam.bank.api.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

}
