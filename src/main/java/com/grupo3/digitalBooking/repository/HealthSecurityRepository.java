package com.grupo3.digitalBooking.repository;

import com.grupo3.digitalBooking.model.HealthSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface HealthSecurityRepository extends JpaRepository<HealthSecurity,Long> {

    @Transactional
    void removeByProductId(Long Id);
}
