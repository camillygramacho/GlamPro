package com.glampro.registerclient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingClientResponseDTO {

    private UUID id;

    private String nameService;

    private String email;

    private String name;

    private String city;

    private String date;

    private String time;

    private String dateScheduling;

}
