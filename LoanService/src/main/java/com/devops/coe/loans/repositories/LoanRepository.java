package com.devops.coe.loans.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devops.coe.loans.models.Loan;
import com.devops.coe.user.ApplicationUsers;

public interface LoanRepository  extends JpaRepository<Loan, Long>{
	List<Loan> findByUserId(Long userId);
}
