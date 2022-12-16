package com.grupo3.digitalBooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name= "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    private String description;
    @Column
    private int stars;
    @Column
    private int rate;
    @Column
    private String address;
    @Column
    private String longitude;
    @Column
    private String latitude;
    @Column
    private String nearLocation;
    @Column
    private String slogan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categories_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cities_id")
    private City city;

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private Set<Image> images;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "products_has_features",
            joinColumns = @JoinColumn(name = "products_id"),
            inverseJoinColumns = @JoinColumn(name = "features_id")
    )
    private Set<Feature> features;

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private Set<Rule> rules;

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private Set<HealthSecurity> healthSecurity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private Set<Policy> policies;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Booking> bookings;

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<Favourite> favourites;

}
