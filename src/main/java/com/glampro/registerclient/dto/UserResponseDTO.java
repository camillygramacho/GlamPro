package com.glampro.registerclient.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private UUID id;

    private String name;

    private String email;

    private String phone;

    private String cpf;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;

    private boolean isClient;

    private boolean isProfessional;

    private String street;

    private String number;

    private String complement;

    private String city;

    private String state;

    private String zipCode;

}
