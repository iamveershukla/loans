package com.devops.coe.loans.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@IdClass(InterestRateId.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InterestRate {
	
	@Id
	@Column(name = "loan_type")
	private String loanType;
	
	@Id
	@Column(name = "credit_rating")
	private String creditRating;
	
	@Column(name = "interest_rate")
	private BigDecimal interestRate;

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getCreditRating() {
		return creditRating;
	}

	public void setCreditRating(String creditRating) {
		this.creditRating = creditRating;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
	
}
