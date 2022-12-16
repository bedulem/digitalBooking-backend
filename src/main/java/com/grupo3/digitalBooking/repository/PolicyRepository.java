package com.grupo3.digitalBooking.repository;

import com.grupo3.digitalBooking.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PolicyRepository extends JpaRepository<Policy,Long> {

    @Transactional
    void removeByProductId(Long Id);
}
