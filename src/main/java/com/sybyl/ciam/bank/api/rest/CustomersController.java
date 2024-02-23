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

import com.sybyl.ciam.bank.api.model.Customer;
import com.sybyl.ciam.bank.api.repository.CustomerRepository;

@RestController
@RequestMapping(path="/bank/customers", produces="application/json")
@CrossOrigin(origins="*")	//http://localhost:8080
public class CustomersController {
	
	private final CustomerRepository customerRepo;	
	
	public CustomersController(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}
	
	@GetMapping
	public Iterable<Customer> allCustomers() {
		return customerRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> customerById(@PathVariable("id") int id) {
		Optional<Customer> optCustomer = customerRepo.findById(id);
		
		if(optCustomer.isPresent()) {
			return new ResponseEntity<>(optCustomer.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Customer createCustomer(@RequestBody Customer customer) {
		customer.setDateRegistered(LocalDateTime.now());	//new Timestamp(System.currentTimeMillis()));
		
		Customer savedCustomer = customerRepo.save(customer); 
		return savedCustomer;
	}
	
	@PutMapping(path="/{customerId}", consumes="application/json")
	public Customer updateCustomer(@PathVariable("customerId") int customerId, @RequestBody Customer customer) {
		customer.setCustomerID(customerId);
		return customerRepo.save(customer);
	}
	
	@DeleteMapping("/{customerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable("customerId") int customerId) {
		try {
			customerRepo.deleteById(customerId);
		} catch(EmptyResultDataAccessException e) {}
	}
}