package com.grupo3.digitalBooking.repository;

import com.grupo3.digitalBooking.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository  <Category, Long>{
}
