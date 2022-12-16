package com.grupo3.digitalBooking.service.DAO;

import com.grupo3.digitalBooking.model.Booking;

import java.util.Collection;
import java.util.Optional;

public interface IBookingService {

    void createBooking(Booking booking);
    Booking updateBooking (Booking booking);
    Collection<Booking> readBookings ();
    Optional<Booking> readBooking (Long id);
    void deleteBooking(Long id);
}
