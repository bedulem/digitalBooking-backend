package com.grupo3.digitalBooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name= "favourites")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favourite {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id")
    private MyUser user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "products_id")
    private Product product;


}
