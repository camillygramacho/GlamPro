package com.glampro.registerclient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private String name;

    private String email;

    private String cpf;

    private String password;

    private String phone;

    private String birthDate;

    private AddressDTO address;

    private boolean client;

    private boolean professional;

    private boolean company;

}
