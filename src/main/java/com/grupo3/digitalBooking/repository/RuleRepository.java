package com.grupo3.digitalBooking.repository;

import com.grupo3.digitalBooking.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RuleRepository extends JpaRepository<Rule,Long> {

    @Transactional
    void removeByProductId(Long Id);
}
