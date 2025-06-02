package com.glampro.registerclient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingPatchDTO {

    private String emailUserLogin;

    private String idServiceSalon;

    private String emailProfessional;

}
