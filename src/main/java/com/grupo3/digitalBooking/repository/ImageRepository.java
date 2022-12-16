package com.grupo3.digitalBooking.repository;

import com.grupo3.digitalBooking.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Transactional
    void removeByProductId(Long Id);
}
