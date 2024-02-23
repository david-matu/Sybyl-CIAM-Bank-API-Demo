package com.sybyl.ciam.bank.api.rest;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sybyl.ciam.bank.api.model.Account;
import com.sybyl.ciam.bank.api.repository.AccountRepository;

@RestController
@RequestMapping(path="/bank/accounts", produces="application/json")
@CrossOrigin(origins="*")	//http://localhost:8080
public class AccountsController {
	
	private final AccountRepository accountRepo;	
	
	public AccountsController(AccountRepository accountRepo) {
		this.accountRepo = accountRepo;
	}
	
	@GetMapping
	public Iterable<Account> allAccounts() {
		return accountRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Account> accountById(@PathVariable("id") int id) {
		Optional<Account> optAccount = accountRepo.findById(id);
		
		if(optAccount.isPresent()) {
			return new ResponseEntity<>(optAccount.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Account createAccount(@RequestBody Account account) {
		account.setDateCreated(LocalDateTime.now());	//new Timestamp(System.currentTimeMillis()));
		
		Account savedAccount = accountRepo.save(account); 
		return savedAccount;
	}
	
	@PutMapping(path="/{accountId}", consumes="application/json")
	public Account updateAccount(@PathVariable("accountId") int accountId, @RequestBody Account account) {
		account.setAccountID(accountId);
		return accountRepo.save(account);
	}
	
	@DeleteMapping("/{accountId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAccount(@PathVariable("accountId") int accountId) {
		try {
			accountRepo.deleteById(accountId);
		} catch(EmptyResultDataAccessException e) {}
	}
}