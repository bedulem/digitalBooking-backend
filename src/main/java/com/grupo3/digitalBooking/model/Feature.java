package com.grupo3.digitalBooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name= "features")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feature {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int iconId;

    @ManyToMany(mappedBy = "features")
    @JsonIgnore
    private Set<Product> products;
}
