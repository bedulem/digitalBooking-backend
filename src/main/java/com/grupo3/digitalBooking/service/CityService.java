package com.grupo3.digitalBooking.service;

import com.grupo3.digitalBooking.model.City;
import com.grupo3.digitalBooking.repository.CityRepository;
import com.grupo3.digitalBooking.service.DAO.ICityService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CityService implements ICityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void createCity(City city) {
        cityRepository.save(city);
    }

    @Override
    public City updateCity(City city) {
        return null;
    }

    @Override
    public Collection<City> readCities() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> readCity(Long id) {

        Optional<City> city = cityRepository.findById(id);
        return city;
    }

    @Override
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}
