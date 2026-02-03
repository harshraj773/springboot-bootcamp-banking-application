package com.mybank.banking_app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔐 Authentication
    @Column(unique = true, nullable = false)
    private String username;     // email / mobile / loginId

    @Column(nullable = false)
    private String password;     // hashed (BCrypt)

    // 🛡 Security lifecycle
    private boolean enabled = true;
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;

    // 🧑 Authorization
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    // 🔗 Link to real person
    @ManyToOne(optional = false)
    private Person person;

    // 🕵 Audit fields
    private LocalDateTime lastLoginAt;
    private int failedLoginAttempts;
}

