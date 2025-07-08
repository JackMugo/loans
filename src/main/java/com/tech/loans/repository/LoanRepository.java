package com.tech.loans.repository;

import com.tech.loans.entity.Client;
import com.tech.loans.entity.Loan;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("SELECT COUNT(l) FROM Loan l WHERE l.status = 'ACTIVE'")
    long countActiveLoans();

    @Query("SELECT SUM(l.amount) FROM Loan l")
    BigDecimal getTotalLoanAmount();

    @Query("SELECT l.client FROM Loan l WHERE l.amount > :threshold AND l.status = 'ACTIVE'")
    List<Client> findClientsWithOutstandingLoans(@Param("threshold") BigDecimal threshold);

}
