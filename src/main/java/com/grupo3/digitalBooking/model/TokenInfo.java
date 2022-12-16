package com.grupo3.digitalBooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenInfo {


    String jwtToken;
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String city;
    private Role role;




}
