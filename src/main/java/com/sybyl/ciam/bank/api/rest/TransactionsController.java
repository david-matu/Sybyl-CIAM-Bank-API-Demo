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

import com.sybyl.ciam.bank.api.model.Transaction;
import com.sybyl.ciam.bank.api.repository.TransactionRepository;

@RestController
@RequestMapping(path="/bank/transactions", produces="application/json")
@CrossOrigin(origins="*")	//http://localhost:8080
public class TransactionsController {
	
	private final TransactionRepository transactionRepo;	
	
	public TransactionsController(TransactionRepository transactionRepo) {
		this.transactionRepo = transactionRepo;
	}
	
	@GetMapping
	public Iterable<Transaction> allTransactions() {
		return transactionRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Transaction> transactionById(@PathVariable("id") int id) {
		Optional<Transaction> optTransaction = transactionRepo.findById(id);
		
		if(optTransaction.isPresent()) {
			return new ResponseEntity<>(optTransaction.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Transaction createTransaction(@RequestBody Transaction transaction) {
		transaction.setTransactionDate(LocalDateTime.now());	//new Timestamp(System.currentTimeMillis()));
		
		Transaction savedTransaction = transactionRepo.save(transaction); 
		return savedTransaction;
	}
	
	@PutMapping(path="/{transactionId}", consumes="application/json")
	public Transaction updateTransaction(@PathVariable("transactionId") int transactionId, @RequestBody Transaction transaction) {
		transaction.setTransactionID(transactionId);
		return transactionRepo.save(transaction);
	}
	
	@DeleteMapping("/{transactionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTransaction(@PathVariable("transactionId") int transactionId) {
		try {
			transactionRepo.deleteById(transactionId);
		} catch(EmptyResultDataAccessException e) {}
	}
}