import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { API_URL } from 'src/app/app.constants';
import { Loan } from 'src/app/home/home.component';
import { LoanEligibilityReq, LoanEligibilityRes } from 'src/app/loan/loan.component';

@Injectable({
  providedIn: 'root'
})
export class LoanDataService {

  constructor(private http:HttpClient) { }

  retrieveAllLoans() {
    return this.http.get<Loan[]>(`${API_URL}/loans`);
  }

  getLoanElegibility(loanEligibilityReq: LoanEligibilityReq) {
    return this.http.post<LoanEligibilityRes>(`${API_URL}/loans/eligibility`, loanEligibilityReq);
  }

  applyNewLoan(loanEligibilityReq: LoanEligibilityReq) {
    return this.http.post<LoanEligibilityRes>(`${API_URL}/loans/apply`, loanEligibilityReq);
  }
}
