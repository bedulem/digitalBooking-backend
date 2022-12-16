package com.grupo3.digitalBooking.controller;

import com.grupo3.digitalBooking.exceptions.InvalidDataResource;
import com.grupo3.digitalBooking.exceptions.ResourceNotFoundException;
import com.grupo3.digitalBooking.model.City;
import com.grupo3.digitalBooking.service.CityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    final static Logger log = Logger.getLogger(CityController.class);

    @Autowired
    CityService cityService;

    @PostMapping()
    public ResponseEntity<HttpStatus> createCity (@RequestBody City city) {
        cityService.createCity(city);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<Collection<City>> readCities () {
        return ResponseEntity.ok(cityService.readCities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> readCity (@PathVariable Long id) throws ResourceNotFoundException {
        City city = cityService.readCity(id).orElseThrow(()->{
            log.error("City with id " + id + " not found");
            return new ResourceNotFoundException(String.format("City with id %s not found", id));
        });
        return ResponseEntity.ok(city);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCity (@PathVariable Long id) throws InvalidDataResource, ResourceNotFoundException {

        if(id <= 0) {
            throw new InvalidDataResource(String.format("Enter a valid id"));
        }

        City city = cityService.readCity(id).orElseThrow(()-> {
            log.error("City with id " + id + " not found");
            return new ResourceNotFoundException(String.format("City with id %s not found", id));
        });
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
