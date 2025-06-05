package com.meu.capital.api.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Adress {
    private String street;
    private String neighborhood;
    private String postalCode;
    private String city;
    private String state;
    private String number;
}
