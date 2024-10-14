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

    @Column(name = "rua", length = 50)
    private String street;

    @Column(name = "número", length = 5)
    private String number;

    @Column(name = "complemento", length = 50)
    private String complement;

    @Column(name = "vizinhança")
    private String neighborhood;

    @Column(name = "cidade")
    private String city;

    @Column(name = "estado" , length = 2)
    private String state;

    @Column(name = "cep", length = 8)
    private String zipCode;

    @OneToOne
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private User user;

    private LocalDateTime dateRegistration;

    private LocalDateTime dateUpdate;

}




