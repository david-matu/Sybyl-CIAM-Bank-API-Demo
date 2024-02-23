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
@Table(name="payment")
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Payment {
	
	public Payment(int senderAccountID, int recipientAccountID, double amount) {
		this.refSenderAccountID = senderAccountID;
		this.refRecipientAccountID = recipientAccountID;
		this.amount = amount;
		this.setPaymentDate(LocalDateTime.now()); //(System.currentTimeMillis()));
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PAYMENT_ID")
	private int paymentID;
	
	@Column(name = "SENDER_ACCOUNT_ID")
	private double refSenderAccountID;
	
	@Column(name = "RECIPIENT_ACCOUNT_ID")
	private double refRecipientAccountID;
	
	private double amount;
	private LocalDateTime paymentDate;
	private String status;
}
