package com.devops.coe.loans.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class InterestRateId implements Serializable{
	
	private String loanType;
	private String creditRating;
 
	public InterestRateId() {
    }
 
    public InterestRateId(String loanType, String creditRating) {
        this.loanType = loanType;
        this.creditRating = creditRating;
    }

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

}
