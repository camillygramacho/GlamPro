package com.glampro.registerclient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingDTO {

    private Long id;

    private String customerName;

    private String serviceName;

    private LocalDateTime appointmentDate;

    private String status;

}
