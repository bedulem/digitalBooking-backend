package com.grupo3.digitalBooking.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo3.digitalBooking.model.Booking;
import com.grupo3.digitalBooking.model.Product;
import com.grupo3.digitalBooking.model.ProductBookingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class BookingsMapper {

    @Autowired
    ObjectMapper mapper;

    public ArrayList<Map<String, LocalDate>> checkInCheckOutMapper (Collection<Booking> bookings) {
        ArrayList<Map<String, LocalDate>> bookingsList = new ArrayList<>();

        for (Booking booking :bookings) {
            Map<String, LocalDate> bookingAux = new HashMap<>();
            bookingAux.put("checkIn",booking.getCheckIn());
            bookingAux.put("checkOut",booking.getCheckOut());
            bookingsList.add(bookingAux);
        }
        return bookingsList;
    }

    public ProductBookingDto productsBookingMapper (Optional<Product> product, ArrayList<Map<String, LocalDate>> bookings) {
        ProductBookingDto productBookingDto = mapper.convertValue( product,ProductBookingDto.class);
        productBookingDto.setBookings(bookings);

        return productBookingDto;
    }
}

