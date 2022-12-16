package com.grupo3.digitalBooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name= "cities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String country;
    @Column
    private String longitude;
    @Column
    private String latitude;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Product> products;
}
