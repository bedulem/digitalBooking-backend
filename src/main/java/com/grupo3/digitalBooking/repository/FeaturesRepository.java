package com.grupo3.digitalBooking.repository;

import com.grupo3.digitalBooking.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeaturesRepository extends JpaRepository<Feature, Long> {
}
