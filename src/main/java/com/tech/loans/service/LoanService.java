package com.tech.loans.service;

import com.tech.loans.entity.Client;
import com.tech.loans.entity.Loan;
import com.tech.loans.repository.ClientRepository;
import com.tech.loans.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ClientRepository clientRepository;

    private static final BigDecimal WARNING_THRESHOLD = BigDecimal.valueOf(1_000_000);

    // Record new loan application
    public Loan createLoan(Long clientId, BigDecimal amount) {
        if (amount.compareTo(WARNING_THRESHOLD) > 0) {
            System.out.println("⚠️ Warning: Loan amount exceeds 1,000,000!");
        }

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Loan loan = new Loan();
        loan.setClient(client);
        loan.setAmount(amount);
        loan.setStatus(Loan.Status.ACTIVE);
        loan.setIssuedAt(LocalDateTime.now());

        return loanRepository.save(loan);
    }

    // Update existing loan
    public Loan updateLoan(Long id, BigDecimal newAmount) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (newAmount.compareTo(WARNING_THRESHOLD) > 0) {
            System.out.println("⚠️ Warning: Updated loan amount exceeds 1,000,000!");
        }

        loan.setAmount(newAmount);
        return loanRepository.save(loan);
    }

    // Delete loan record
    public void deleteLoan(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        loanRepository.delete(loan);
    }

    // Reports
    public long getActiveLoanCount() {
        return loanRepository.countActiveLoans();
    }

    public BigDecimal getTotalLoanAmountIssued() {
        return loanRepository.getTotalLoanAmount();
    }

    public List<Client> getClientsWithOutstandingLoans(BigDecimal threshold) {
        return loanRepository.findClientsWithOutstandingLoans(threshold);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
}

