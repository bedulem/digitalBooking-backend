package com.grupo3.digitalBooking.repository;

import com.grupo3.digitalBooking.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteRepository extends JpaRepository <Favourite,Long> {

    @Query(value = "SELECT * FROM favourites WHERE favourites.users_id = ?1 ", nativeQuery = true)
    List<Favourite> findByUserId(Long myUserId);

    @Query(value = "SELECT * FROM favourites WHERE favourites.users_id = ?1 and favourites.products_id = ?2", nativeQuery = true)
    Favourite findByProductId(Long userId, Long productId);
}
