package com.devops.coe.creditrating.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CreditRating {
	
	@Id
	@Column(name = "pan_card")
	private String panCard;
	
	@Column(name = "credit_rating")
	private String creditRating;
	
	@Column(name = "monthly_surplus")
    private BigDecimal monthlySurplus;
	
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
	
}
