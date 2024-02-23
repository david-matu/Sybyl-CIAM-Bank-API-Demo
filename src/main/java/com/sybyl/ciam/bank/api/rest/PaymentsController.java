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

import com.sybyl.ciam.bank.api.model.Payment;
import com.sybyl.ciam.bank.api.repository.PaymentRepository;

@RestController
@RequestMapping(path="/bank/payments", produces="application/json")
@CrossOrigin(origins="*")	//http://localhost:8080
public class PaymentsController {
	
	private final PaymentRepository paymentRepo;	
	
	public PaymentsController(PaymentRepository paymentRepo) {
		this.paymentRepo = paymentRepo;
	}
	
	@GetMapping
	public Iterable<Payment> allPayments() {
		return paymentRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Payment> paymentById(@PathVariable("id") int id) {
		Optional<Payment> optPayment = paymentRepo.findById(id);
		
		if(optPayment.isPresent()) {
			return new ResponseEntity<>(optPayment.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Payment createPayment(@RequestBody Payment payment) {
		payment.setPaymentDate(LocalDateTime.now());	//new Timestamp(System.currentTimeMillis()));
		
		Payment savedPayment = paymentRepo.save(payment); 
		return savedPayment;
	}
	
	@PutMapping(path="/{paymentId}", consumes="application/json")
	public Payment updatePayment(@PathVariable("paymentId") int paymentId, @RequestBody Payment payment) {
		payment.setPaymentID(paymentId);
		return paymentRepo.save(payment);
	}
	
	@DeleteMapping("/{paymentId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePayment(@PathVariable("paymentId") int paymentId) {
		try {
			paymentRepo.deleteById(paymentId);
		} catch(EmptyResultDataAccessException e) {}
	}
}