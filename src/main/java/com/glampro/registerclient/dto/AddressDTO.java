package com.glampro.registerclient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private String street;

    private String number;

    private String complement;

    private String neighborhood;

    private String city;

    private String state;

    private String zipCode;

}
