package com.glampro.registerclient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TAB_USER")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "cpf",length = 20)
    private String cpf;

    @Column(name = "password")
    private String password;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "client")
    private boolean isClient;

    @Column(name = "professional")
    private boolean isProfessional;

    @Column(name = "company")
    private boolean isCompany;

    private LocalDateTime dateRegistration;

    private LocalDateTime dateUpdate;

    @Column(name = "active")
    private boolean isActive;

}
