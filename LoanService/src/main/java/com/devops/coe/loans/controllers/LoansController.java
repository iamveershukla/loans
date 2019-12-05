package com.devops.coe.loans.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devops.coe.jwt.JwtTokenUtil;
import com.devops.coe.loans.models.CreditRating;
import com.devops.coe.loans.models.InterestRate;
import com.devops.coe.loans.models.InterestRateId;
import com.devops.coe.loans.models.Loan;
import com.devops.coe.loans.models.LoanEligibilityReq;
import com.devops.coe.loans.models.LoanEligibilityRes;
import com.devops.coe.loans.repositories.InterestRateRepository;
import com.devops.coe.loans.repositories.LoanRepository;
import com.devops.coe.loans.services.CreditRatingService;
import com.devops.coe.user.ApplicationUserRepository;
import com.devops.coe.user.ApplicationUsers;

@RestController
@RequestMapping("/api/v1/loans")
@CrossOrigin("http://localhost:4200")
public class LoansController {
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private ApplicationUserRepository userRepository;
	
	@Autowired
	private InterestRateRepository interestRateRepository;
	
	@Autowired
	private CreditRatingService creditRatingService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	private static BigDecimal ratePerMonth(BigDecimal rate) {
		  return rate.divide(BigDecimal.valueOf(12), 10, RoundingMode.DOWN);
	}
	
	private static BigDecimal calculateRatePower(BigDecimal rate, int period) {
		  BigDecimal onePlus = BigDecimal.ONE.add(ratePerMonth(rate));
		  return onePlus.pow(period * 12);
	}
	
	private static BigDecimal calculateEmi(BigDecimal interestRate, BigDecimal principalAmount, int loanTerm) {
		interestRate = interestRate.multiply(new BigDecimal(0.01));
		BigDecimal top = ratePerMonth(interestRate).multiply(calculateRatePower(interestRate, loanTerm));
		BigDecimal bottom = BigDecimal.ONE.subtract(calculateRatePower(interestRate, loanTerm));
		BigDecimal ratio = top.divide(bottom, 10, RoundingMode.DOWN);
		
		BigDecimal emi = principalAmount.multiply(ratio).multiply(new BigDecimal(-1.00));
		
		emi = emi.setScale(0, BigDecimal.ROUND_UP);

		return emi;
	}
	
	private BigDecimal getInterestRate(String loanType, String creditRating) {
		final List<InterestRate> interestRatesList = interestRateRepository.findAll();
		
		BigDecimal iRate = null;
		
		InterestRateId interestRateId = new
		InterestRateId(loanType.toUpperCase().replace(' ', '_'), creditRating );
		  
		for (InterestRate interestRate : interestRatesList) { 
			if (interestRateId.getCreditRating().equals(interestRate.getCreditRating()) &&
				(interestRateId.getLoanType().equals(interestRate.getLoanType()))){
					iRate = interestRate.getInterestRate(); 
					break; 
			}
		}
		return iRate;
	}
	
	private ApplicationUsers getApplicationUserFromJwtToken(String jwtToken) {
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken.substring(7));
		ApplicationUsers applicationUser = userRepository.findByUsername(username);
		
		return applicationUser;
	}
	
	private String getLoanApproval(BigDecimal monthlySurplus, BigDecimal emi) {
		
		int difference = monthlySurplus.compareTo(emi);
		if (difference == -1)
			return "Rejected";
		else
			return "Approved";
	}
	
	private String getLoanApprovalMessage(String loanApproval, String loanType, BigDecimal loanAmount, BigDecimal emi, Integer tenure) {
		
		if (loanApproval.equals("Approved"))
			return "You are Eligible for " + loanType + " of " + loanAmount.toString() + " and tenure of " + tenure + " years. EMI will be: " + emi.toString();
		else
			return "You are Not Eligible for " + loanType + " of " + loanAmount.toString() + " and tenure of " + tenure + " years.";
	}
	
	private String getLoanApplicationMessage(String loanApproval, String loanType, BigDecimal loanAmount, BigDecimal emi, Integer tenure) {
		
		if (loanApproval.equals("Approved"))
			return "Congratulations !!! You have successfully applied for " + loanType + " of " + loanAmount.toString() + " and tenure of " + tenure + " years. EMI is: " + emi.toString();
		else
			return "You are Not Eligible for " + loanType + " of " + loanAmount.toString() + " and tenure of " + tenure + " years.";
	}
	
	@GetMapping
	public List<Loan> list(@RequestHeader("Authorization") String requestTokenHeader){
		ApplicationUsers applicationUserDetails = getApplicationUserFromJwtToken(requestTokenHeader);
		
		return loanRepository.findByUserId(applicationUserDetails.getUserId());
	}
	
	@PostMapping
	public void create(@RequestBody Loan loan) {
		loanRepository.save(loan);
	}
	
	@PostMapping("/eligibility")
	public LoanEligibilityRes loanEligibility(@RequestBody LoanEligibilityReq loanEligibilityReq, @RequestHeader("Authorization") String requestTokenHeader){

		ApplicationUsers applicationUserDetails = getApplicationUserFromJwtToken(requestTokenHeader);
		CreditRating creditRating = creditRatingService.getCreditRating(applicationUserDetails.getPanCard());
		BigDecimal interestRate = getInterestRate(loanEligibilityReq.getLoanType(), creditRating.getCreditRating());
		BigDecimal emi = calculateEmi(interestRate, loanEligibilityReq.getLoanAmount(), loanEligibilityReq.getTenure());
		String loanApproval = getLoanApproval(creditRating.getMonthlySurplus(), emi);
		String loanApprovalMessage = getLoanApprovalMessage(loanApproval, loanEligibilityReq.getLoanType(), loanEligibilityReq.getLoanAmount(), emi, loanEligibilityReq.getTenure());
		
		LoanEligibilityRes loanEligibilityRes = new LoanEligibilityRes(loanEligibilityReq.getLoanType(), loanEligibilityReq.getLoanAmount(), 
				loanEligibilityReq.getTenure(), interestRate, emi, loanApproval, loanApprovalMessage);
		
		return loanEligibilityRes;
	}
	
	@PostMapping("/apply")
	public LoanEligibilityRes loanApplication(@RequestBody LoanEligibilityReq loanEligibilityReq, @RequestHeader("Authorization") String requestTokenHeader){

		ApplicationUsers applicationUserDetails = getApplicationUserFromJwtToken(requestTokenHeader);
		CreditRating creditRating = creditRatingService.getCreditRating(applicationUserDetails.getPanCard());
		BigDecimal interestRate = getInterestRate(loanEligibilityReq.getLoanType(), creditRating.getCreditRating());
		BigDecimal emi = calculateEmi(interestRate, loanEligibilityReq.getLoanAmount(), loanEligibilityReq.getTenure());
		String loanApplication = getLoanApproval(creditRating.getMonthlySurplus(), emi);
		String loanApplicationMessage = getLoanApplicationMessage(loanApplication, loanEligibilityReq.getLoanType(), loanEligibilityReq.getLoanAmount(), emi, loanEligibilityReq.getTenure());
		
		
		Loan loan = new Loan(applicationUserDetails.getUserId(), loanEligibilityReq.getLoanType(), loanEligibilityReq.getLoanAmount(), emi, loanEligibilityReq.getTenure() * 12);
		System.out.println("Loan Application: " + loan.toString());
		
		loanRepository.save(loan);
		
		LoanEligibilityRes loanEligibilityRes = new LoanEligibilityRes(loanEligibilityReq.getLoanType(), loanEligibilityReq.getLoanAmount(), 
				loanEligibilityReq.getTenure(), interestRate, emi, loanApplication, loanApplicationMessage);
		
		return loanEligibilityRes;
	}
	
	@GetMapping("/{loanId}")
	public Loan get(@PathVariable("loanId") long loanId){
		return loanRepository.getOne(loanId);		
	}

}
