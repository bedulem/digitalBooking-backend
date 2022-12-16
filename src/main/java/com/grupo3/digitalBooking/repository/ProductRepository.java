package com.grupo3.digitalBooking.repository;

import com.grupo3.digitalBooking.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;



@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByCityId(Long cityId);

   @Query(value = "SELECT * FROM products WHERE  products.cities_id = ?1 ORDER BY ?2 LIMIT ?3 OFFSET ?4", nativeQuery = true)
   List<Product> findByCity(Long cityId, String order, int limit, int offset);

    @Query(value = "SELECT * FROM products WHERE products.categories_id = ?1 ORDER BY ?2 LIMIT ?3 OFFSET ?4", nativeQuery = true)
    List<Product> findByCategory(Long categoryId, String order, int limit, int offset);

    @Query(value = "SELECT * FROM products WHERE products.cities_id= ?1 and products.categories_id = ?2 ORDER BY ?3 LIMIT ?4 OFFSET ?5", nativeQuery = true)
    List<Product> findByCityAndCategory(Long cityId, Long  categoryId, String order, int limit, int offset);

    @Query(value = "SELECT * FROM products ORDER BY ?1 LIMIT ?2 OFFSET ?3", nativeQuery = true)
    List<Product> findAllPaginated(String order, int limit, int offset);

    //(
    @Query(value = "SELECT * FROM products WHERE products.cities_id= ?1 AND products.id NOT IN (SELECT DISTINCT bookings.products_id FROM bookings WHERE bookings.check_out > ?2 and bookings.check_in < ?3)", nativeQuery = true)
    List<Product> findByCityAndDate(Long citiId, LocalDate checkIn, LocalDate checkOut);

    @Query(value = "SELECT * FROM products WHERE products.cities_id= ?1 AND products.categories_id = ?4 AND products.id NOT IN (SELECT DISTINCT bookings.products_id FROM bookings WHERE bookings.check_out > ?2 and bookings.check_in < ?3)", nativeQuery = true)
    List<Product> findByCityAndDateAndCategory(Long citiId, LocalDate checkIn, LocalDate checkOut,Long categoryId);

    //random
    @Query(value = "SELECT * FROM products ORDER BY rand() LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<Product> findAllPaginatedRandom( int limit, int offset);

    @Query(value = "SELECT * FROM products WHERE products.cities_id= ?1 and products.categories_id = ?2 ORDER BY rand() LIMIT ?3 OFFSET ?4", nativeQuery = true)
    List<Product> findAllPaginatedRandomCityAndCategory( Long cityId, Long  categoryId, int limit, int offset);

    @Query(value = "SELECT * FROM products WHERE products.cities_id= ?1 ORDER BY rand() LIMIT ?2 OFFSET ?3", nativeQuery = true)
    List<Product> findAllPaginatedRandomCity( Long cityId, int limit, int offset);

    @Query(value = "SELECT * FROM products WHERE products.categories_id= ?1  ORDER BY rand() LIMIT ?2 OFFSET ?3", nativeQuery = true)
    List<Product> findAllPaginatedRandomCategory( Long categoryId, int limit, int offset);

}
