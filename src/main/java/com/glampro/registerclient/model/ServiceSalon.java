package com.glampro.registerclient.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Entity
@Table(name = "TAB_SERVICE_SALON")
@NoArgsConstructor
@AllArgsConstructor
public class ServiceSalon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @JsonIgnore
    private User professional;

    @Column(name = "nameService", nullable = false, length = 100)
    private String nameService;

    @Column(name = "valueService")
    private BigDecimal valueService;

    @Column(name = "active")
    private boolean active;

    @Column(name = "dateUpdate")
    private LocalDateTime dateUpdate;

}
