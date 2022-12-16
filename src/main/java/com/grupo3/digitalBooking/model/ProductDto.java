package com.grupo3.digitalBooking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

    private Long id;
    private String title;
    private String description;
    private int stars;
    private int rate;
    private String address;
    private String longitude;
    private String latitude;
    private String nearLocation;
    private String slogan;
    private Category category;
    private City city;

    private Set<?> images;
    private Set<FeatureDto> features;
    private List<?> rules;
    private Set<?> healthSecurity;
    private Set<?> policies;
    private Set<?> users;
    private Set<Favourite> favourites;

}
