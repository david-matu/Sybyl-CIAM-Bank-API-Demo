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
@Table(name="transaction")
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Transaction {
	
	/**
	 * 
	 * @param accountID
	 * @param transactionType: C-Credit, D-Debit
	 * @param amount
	 */
	public Transaction(int accountID, String transactionType, double amount) {
		this.refAccountID = accountID;
		this.transactionType = transactionType;
		this.amount = amount;
		this.setTransactionDate(LocalDateTime.now()); //(System.currentTimeMillis()));
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANX_ID")
	private int transactionID;
	
	@Column(name = "ACCOUNT_ID")
	private long refAccountID;
	
	@Column(name = "TRANX_TYPE")
	private String transactionType;
	
	private double amount;
	
	private String comment;
	
	@Column(name = "TRANX_DATE")
	private LocalDateTime transactionDate;
}
