package com.meu.capital.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AdressDTO (
        @NotBlank
        String street,

        @NotBlank
        String neighborhood,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String postalCode,

        @NotBlank
        String city,

        @NotBlank
        String state,

        @NotBlank
        String number
){

}
