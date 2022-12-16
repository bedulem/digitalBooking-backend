package com.grupo3.digitalBooking.service.DAO;

import com.grupo3.digitalBooking.model.City;


import java.util.Collection;
import java.util.Optional;

public interface ICityService {
    void createCity(City city);
    City updateCity (City city);
    Collection<City> readCities ();
    Optional<City> readCity (Long id);
    void deleteCity(Long id);
}
