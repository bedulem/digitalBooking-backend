package com.grupo3.digitalBooking.repository;

import com.grupo3.digitalBooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    @Query(value = "SELECT * FROM bookings WHERE products_id = ?1 ", nativeQuery = true)
    List<Booking> findByProductsId(Long id);

    @Query(value = "SELECT * FROM bookings WHERE users_id = ?1 ", nativeQuery = true)
    List<Booking> findByUserId(Long id);
}
