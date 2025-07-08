package com.tech.loans.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "national_id", nullable = false, unique = true)
    private String nationalId;

    private String name;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loan> loans = new ArrayList<>();
}
