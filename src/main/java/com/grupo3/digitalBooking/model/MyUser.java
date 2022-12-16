package com.grupo3.digitalBooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name= "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roles_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Booking> bookings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<Favourite> favourites;

}
