package com.devops.coe.loans.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_id")
	private Long id;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "loan_type")
	private String loanType;
	
	private BigDecimal amount;
	private BigDecimal emi;
	
	private Integer installments;
	
	public Loan(){
		super();
	}
	
	public Loan(Long userId, String loanType, BigDecimal amount, BigDecimal emi, Integer installments) {
		super();
		this.userId = userId;
		this.loanType = loanType;
		this.amount = amount;
		this.emi = emi;
		this.installments = installments;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public BigDecimal getLoanAmount() {
		return amount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.amount = loanAmount;
	}
	public BigDecimal getEmi() {
		return emi;
	}
	public void setEmi(BigDecimal emi) {
		this.emi = emi;
	}
	public Integer getInstallments() {
		return installments;
	}
	public void setInstallments(Integer installments) {
		this.installments = installments;
	}

	@Override
	public String toString() {
		return "Loan [id=" + id + ", userId=" + userId + ", loanType=" + loanType + ", amount=" + amount + ", emi="
				+ emi + ", installments=" + installments + "]";
	}
}
