package com.mybank.banking_app.repositories;

import com.mybank.banking_app.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
     boolean existsByEmail(String email);

     boolean existsByContactNumber(String contactNumber);
}
