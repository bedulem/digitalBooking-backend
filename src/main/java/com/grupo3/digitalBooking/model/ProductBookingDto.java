package com.grupo3.digitalBooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductBookingDto {

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
    private Set<Image> images;
    private Set<Feature> features;
    private Set<Rule> rules;
    private Set<HealthSecurity> healthSecurity;
    private Set<Policy> policies;
    private ArrayList<Map<String, LocalDate>> bookings;
    private Set<Favourite> favourites;

}
