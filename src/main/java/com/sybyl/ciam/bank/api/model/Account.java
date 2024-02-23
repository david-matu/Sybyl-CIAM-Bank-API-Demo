package com.sybyl.ciam.bank.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="account")
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_ID")
	private int accountID;
	
	@Column(name = "CUSTOMER_ID")
	private long refCustomerID;
	
	private String accountType;
	
	@Column(name = "AVAILABLE_BALANCE")
	private double balanceAvailable;
	
	@Column(name = "CURRENT_BALANCE")
	private double balanceCurrent;
	
	private LocalDateTime dateCreated;
	
	private String status;
	private String accountLog;
}
