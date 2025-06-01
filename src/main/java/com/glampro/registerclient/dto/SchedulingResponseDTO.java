package com.glampro.registerclient.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingResponseDTO {

    private UUID id;

    private String nameService;

    private String email;

    private String name;

    private String city;

    LocalDateTime dateScheduling;
}
