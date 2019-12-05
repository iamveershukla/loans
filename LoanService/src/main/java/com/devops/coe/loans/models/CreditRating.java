package com.devops.coe.loans.models;

import java.math.BigDecimal;

import javax.persistence.Entity;

public class CreditRating {
	
	private String panCard;
	
	private String creditRating;

	private BigDecimal monthlySurplus;
	
	public CreditRating() {
		super();
	}

	public CreditRating(String panCard, String creditRating, BigDecimal monthlySurplus) {
		super();
		this.panCard = panCard;
		this.creditRating = creditRating;
		this.monthlySurplus = monthlySurplus;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getCreditRating() {
		return creditRating;
	}

	public void setCreditRating(String creditRating) {
		this.creditRating = creditRating;
	}

	public BigDecimal getMonthlySurplus() {
		return monthlySurplus;
	}

	public void setMonthlySurplus(BigDecimal monthlySurplus) {
		this.monthlySurplus = monthlySurplus;
	}

	@Override
	public String toString() {
		return "CreditRating [panCard=" + panCard + ", creditRating=" + creditRating + ", monthlySurplus="
				+ monthlySurplus + "]";
	}

}
