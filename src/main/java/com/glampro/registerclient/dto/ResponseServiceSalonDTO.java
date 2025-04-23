package com.glampro.registerclient.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseServiceSalonDTO {

    private String id;

    private String idProfessional;

    private String nameService;

    private BigDecimal valueService;

}
