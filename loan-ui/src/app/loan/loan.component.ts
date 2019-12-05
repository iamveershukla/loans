import { Component, OnInit, ViewChild } from '@angular/core';
import { LoanDataService } from '../service/data/loan-data.service';
import { Router } from '@angular/router';

export class Loan {
  constructor(
    public loanType: string,
    public loanAmount: string,
    public tenure: string,
    public interestRate: string,
    public emi: number,
    public installments: number
  ){  }
}

export class LoanEligibilityReq {
  constructor(
    public loanType: string,
    public loanAmount: string,
    public tenure: string
  ){  }
}

export class LoanEligibilityRes {
  constructor(
    public emi: number,
    public interestRate: string,
    public loanAmount: string,
    public loanType: string,
    public message: string,
    public status: string,
    public tenure: string
  ){  }
}

@Component({
  selector: 'app-loan',
  templateUrl: './loan.component.html',
  styleUrls: ['./loan.component.css']
})
export class LoanComponent implements OnInit {

  loan: Loan;
  loanEligibilityReq: LoanEligibilityReq;
  loanEligibilityRes: LoanEligibilityRes;
  message: string;
  status: string;

  constructor(private loanService: LoanDataService, private router : Router) { }

  ngOnInit() {
    this.loan = new Loan("", "", "", "", 0, 0);
    this.loanEligibilityReq = new LoanEligibilityReq("", "", "");
    this.loanEligibilityRes = new LoanEligibilityRes(0, "", "", "", "", "", "");
    this.message = "";
    this.status = "";
  }

  getLoanElegibility(){

    this.loanEligibilityReq.loanType = this.loan.loanType;
    this.loanEligibilityReq.loanAmount = this.loan.loanAmount;
    this.loanEligibilityReq.tenure = this.loan.tenure;

    this.loanService.getLoanElegibility(this.loanEligibilityReq).subscribe(
      response => {
        this.loanEligibilityRes = response;
        this.message = response.message;
        this.status = response.status;
      }
    )
  }

  resetApplication(){
    this.loan.loanType = '';
    this.loan.loanAmount = '';
    this.loan.tenure = '';
    this.loan.interestRate = '';
    this.loan.emi = 0;
    this.loan.installments = 0;
  }

  applyNewLoan(){

    this.loanEligibilityReq.loanType = this.loan.loanType;
    this.loanEligibilityReq.loanAmount = this.loan.loanAmount;
    this.loanEligibilityReq.tenure = this.loan.tenure;

    this.loanService.applyNewLoan(this.loanEligibilityReq).subscribe(
      response => {
        this.loanEligibilityRes = response;
        this.message = this.loanEligibilityRes.message;
        this.status = response.status;
        this.router.navigate(['home']);
      }
    )
   
  }

}
