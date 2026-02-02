package com.mybank.banking_app.entities;

import com.mybank.banking_app.enums.AddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address", uniqueConstraints = @UniqueConstraint(columnNames = "branch_id"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "pincode", nullable = false, length = 6)
    private String pincode;

    @Column(name = "state", nullable = false)
    private String state;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type", nullable = false)
    private AddressType addressType;

    // N addresses can belong to same person
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    // branch can have only one address
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", unique = true)
    private Branch branch;
}
