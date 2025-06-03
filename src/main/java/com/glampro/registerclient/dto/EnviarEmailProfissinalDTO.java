package com.glampro.registerclient.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnviarEmailProfissinalDTO {

    private String emailProfessional;

    private String emailClient;

    private String nameProfessional;

    private String nameClient;

    private StringBuilder servico;

}
