package com.devops.coe.loans.models;

import java.math.BigDecimal;

public class LoanEligibilityReq {
	
	private String loanType;
	private BigDecimal loanAmount;
	private Integer tenure;
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
}
