package com.grupo3.digitalBooking.controller;

import com.grupo3.digitalBooking.exceptions.InvalidDataResource;
import com.grupo3.digitalBooking.exceptions.ResourceNotFoundException;
import com.grupo3.digitalBooking.model.Booking;
import com.grupo3.digitalBooking.service.BookingService;
import com.grupo3.digitalBooking.util.BookingsMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    final static Logger log = Logger.getLogger(BookingController.class);

    @Autowired
    BookingService bookingService;

    @Autowired
    BookingsMapper bookingsMapper;


    @PostMapping()
    public ResponseEntity<HttpStatus> createBooking (@RequestBody Booking booking) {
        bookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<Collection<Booking>> readBookings () {
        return ResponseEntity.ok(bookingService.readBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> readBooking (@PathVariable Long id) throws ResourceNotFoundException {
        Booking booking = bookingService.readBooking(id).orElseThrow(() -> {
            log.error("Booking with id " + id + " not found");
            return new ResourceNotFoundException(String.format("Product with id %s not found", id));
        });
        booking.getUser().setPassword("****");
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBooking (@PathVariable Long id) throws ResourceNotFoundException, InvalidDataResource {

        if(id <=0 ){
            throw new InvalidDataResource(String.format("Enter a valid id"));
        }

        Booking booking = bookingService.readBooking(id).orElseThrow(()-> {
            log.error("Booking with id " + id + " not found");
            return new ResourceNotFoundException(String.format("Booking with id %s not found", id));
        });

        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> findByProductId (@PathVariable Long id) throws ResourceNotFoundException {
        Collection<Booking> booking = bookingService.findByProductId(id);


        return ResponseEntity.ok(bookingsMapper.checkInCheckOutMapper(booking));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findByUserId (@PathVariable Long id) throws ResourceNotFoundException {
        Collection<Booking> booking = bookingService.findByUserId(id);
        for (Booking bookingAux: booking) {
            bookingAux.getUser().setPassword("****");
        }
        return ResponseEntity.ok(booking);
    }
}
