package com.devops.coe.loans.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoanEligibilityRes implements Serializable{
	private String loanType;
	private BigDecimal loanAmount;
	private Integer tenure;
	private BigDecimal interestRate;
	private BigDecimal emi;
	private String status;
	private String message;
	
	public LoanEligibilityRes(String loanType, BigDecimal loanAmount, Integer tenure, BigDecimal interestRate,
			BigDecimal emi, String status, String message) {
		super();
		this.loanType = loanType;
		this.loanAmount = loanAmount;
		this.tenure = tenure;
		this.interestRate = interestRate;
		this.emi = emi;
		this.status = status;
		this.message = message;
	}
	
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public Integer getTenure() {
		return tenure;
	}
	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}
	public BigDecimal getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
	public BigDecimal getEmi() {
		return emi;
	}
	public void setEmi(BigDecimal emi) {
		this.emi = emi;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
