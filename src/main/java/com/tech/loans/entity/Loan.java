package com.tech.loans.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    private LocalDateTime issuedAt = LocalDateTime.now();

    public enum Status {
        ACTIVE, CLOSED
    }

}
