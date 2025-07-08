package com.tech.loans.controller;

import com.tech.loans.entity.Loan;
import com.tech.loans.service.LoanService;
import dto.LoanDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<Loan> createLoan(@Valid @RequestBody LoanDTO dto) {
        Loan loan = loanService.createLoan(dto.getClientId(), dto.getAmount());
        return ResponseEntity.ok(loan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable Long id, @Valid @RequestBody LoanDTO dto) {
        Loan updated = loanService.updateLoan(id, dto.getAmount());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.ok("Loan deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @GetMapping("/report")
    public ResponseEntity<Map<String, Object>> getLoanReport(@RequestParam(defaultValue = "50000") BigDecimal threshold) {
        Map<String, Object> report = new HashMap<>();
        report.put("activeLoanCount", loanService.getActiveLoanCount());
        report.put("totalLoanAmount", loanService.getTotalLoanAmountIssued());
        report.put("clientsAboveThreshold", loanService.getClientsWithOutstandingLoans(threshold));
        return ResponseEntity.ok(report);
    }
}