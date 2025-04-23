package com.glampro.registerclient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.glampro.registerclient.model.Address;
import jakarta.validation.constraints.*;

@Data
@Entity
@Table(name = "TAB_ADDRESS")
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "street", length = 100)
    private String street;

    @Column(name = "number", length = 10)
    private String number;

    @Column(name = "complement", length = 100)
    private String complement;

    @Column(name = "city")
    private String city;

    @Column(name = "state" , length = 2)
    private String state;

    @Column(name = "zip_code", length = 9)
    private String zipCode;

    @OneToOne
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private User user;

    private LocalDateTime dateRegistration;

    private LocalDateTime dateUpdate;

}




