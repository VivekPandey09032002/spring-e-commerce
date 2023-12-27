package com.vivek.commerce.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class PaymentInformation implements Serializable {
	private static final long serialVersionUID = -3023845191123238624L;
	
	@Column(name="credit_card_number",nullable = false)
	private String creditCardNumber;

	@Column(name="card_holder_name",nullable = false)
	private String cardHolderName;

	@Column(name="expiration_date",nullable = false)
	private LocalDate expirationDate;

	@Column(name="cvv",nullable = false)
	private Integer cvv;
}
