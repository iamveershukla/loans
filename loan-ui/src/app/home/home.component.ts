import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoanDataService } from '../service/data/loan-data.service';

export class Loan {
  constructor(
    public id: number,
    public loanType: string,
    public loanAmount: number,
    public emi: number,
    public installments: number
  ){  }
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  loans: Loan[]

  message: string

  constructor(    private loanService: LoanDataService,
    private router : Router) { }

  ngOnInit() {
    this.refreshLoans();
  }

  refreshLoans(){
    this.loanService.retrieveAllLoans().subscribe(
      response => {
        console.log(response);
        this.loans = response;
      }
    )
  }

  applyNewLoan() {
    this.router.navigate(['loans'])
  }

}
