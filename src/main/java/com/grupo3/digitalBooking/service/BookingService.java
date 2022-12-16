package com.grupo3.digitalBooking.service;

import com.grupo3.digitalBooking.model.Booking;
import com.grupo3.digitalBooking.repository.BookingRepository;
import com.grupo3.digitalBooking.service.DAO.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void createBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Booking booking) {
        return null;
    }

    @Override
    public Collection<Booking> readBookings() {

        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> readBooking (Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking;
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public Collection<Booking> findByProductId(Long id) {
        return bookingRepository.findByProductsId(id);
    }

    public Collection<Booking> findByUserId(Long id) {
        return bookingRepository.findByUserId(id);
    }

}

