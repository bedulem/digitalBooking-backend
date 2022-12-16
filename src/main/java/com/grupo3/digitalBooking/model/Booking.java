package com.grupo3.digitalBooking.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name= "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String startTime;
    @Column
    private LocalDate checkIn;
    @Column
    private LocalDate checkOut;
    @Column
    private Boolean covidVaccinated;
    @Column
    private String othersComments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "products_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id", nullable = false)
    private MyUser user;

}
