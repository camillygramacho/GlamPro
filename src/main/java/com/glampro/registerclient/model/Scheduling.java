package com.glampro.registerclient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TAB_SCHEDULING")
@NoArgsConstructor
@AllArgsConstructor
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "dt_time_available", nullable = false)
    private LocalDateTime dateTimeAvailable;

    @Column(name = "available", nullable = false)
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "user_id_cli")
   // @JsonIgnore
    private User client;

    @ManyToOne
    @JoinColumn(name = "id_serv_salon", nullable = false)
   // @JsonIgnore
    private ServiceSalon serviceSalon;

    @Column(name = "dateScheduling")
    private LocalDateTime dateScheduling;

    @Column(name = "active")
    private boolean active;

    @Column(name = "dateUpdate")
    private LocalDateTime dateUpdate;

}
