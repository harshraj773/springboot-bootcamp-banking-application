package com.mybank.banking_app.repositories;

import com.mybank.banking_app.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByPersonPersonId(Long personId);
}
