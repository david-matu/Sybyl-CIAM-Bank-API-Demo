package com.sybyl.ciam.bank.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.sybyl.ciam.bank.api.model.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

}
