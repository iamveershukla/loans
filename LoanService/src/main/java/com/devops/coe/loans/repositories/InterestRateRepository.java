package com.devops.coe.loans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devops.coe.loans.models.InterestRate;

public interface InterestRateRepository  extends JpaRepository<InterestRate, String>{

}
