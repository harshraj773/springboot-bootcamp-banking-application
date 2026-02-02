package com.mybank.banking_app.repositories;

import com.mybank.banking_app.entities.Branch;
import com.mybank.banking_app.enums.BranchStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    Page<Branch> findByBankBankIdAndBranchStatus(Long bankId, BranchStatus status, Pageable pageable);
}
