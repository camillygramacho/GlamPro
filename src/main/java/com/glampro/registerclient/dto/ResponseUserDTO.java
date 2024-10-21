package com.glampro.registerclient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDTO {

    private String id;

    private String name;

    private String email;

    private String cpf;

    private String phone;

    private String birthDate;

    private AddressDTO address;

    private boolean isClient;

    private boolean isProfessional;

}
