package com.mybank.banking_app.entities;

import com.mybank.banking_app.enums.BranchStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.aop.target.LazyInitTargetSource;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    @Column(name = "branch_name", nullable = false)
    private String branchName;

    @Column(name = "branch_code", nullable = false)
    private String branchCode;

    @Column(name = "ifsc_code", nullable = false, length = 11, unique = true)
    private String branchIfscCode;

    @Column(name = "branch_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private BranchStatus branchStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id",nullable = false)
    private Bank bank;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    private List<Account> accounts;

    @OneToOne(mappedBy = "branch", fetch = FetchType.LAZY)
    private Address address;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    private List<Locker> lockers = new ArrayList<>();
}
