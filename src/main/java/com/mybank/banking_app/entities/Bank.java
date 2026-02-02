package com.mybank.banking_app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;

    @Column(name = "name", nullable = false, length = 100)
    private String bankName;

    @Column(name = "code",nullable = false, length = 20)
    private String bankCode;

    @Column(name = "headquater", nullable = false)
    private String headquater;

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    private List<Branch> branches = new ArrayList<>();
}
