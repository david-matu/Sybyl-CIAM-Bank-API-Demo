package com.sybyl.ciam.bank.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.sybyl.ciam.bank.api.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
