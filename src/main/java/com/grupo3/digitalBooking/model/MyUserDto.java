package com.grupo3.digitalBooking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyUserDto {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String city;
    private Role role;
    private Set<Product> favourites;
}
