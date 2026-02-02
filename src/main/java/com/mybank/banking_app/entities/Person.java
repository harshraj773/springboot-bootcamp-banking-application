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
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "contact_number", nullable = false, length = 10)
    private String contactNumber;

    @Column(name = "pan_number", length = 10)
    private String panNumber;

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
    private Customer customer;

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
    private Employee employee;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<Address> addresses = new ArrayList<>();
}
