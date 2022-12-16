package com.grupo3.digitalBooking.controller;

import com.grupo3.digitalBooking.exceptions.InvalidDataResource;
import com.grupo3.digitalBooking.exceptions.ResourceNotFoundException;
import com.grupo3.digitalBooking.model.Feature;
import com.grupo3.digitalBooking.service.FeatureService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/features")
public class FeatureController {

    final static Logger log = Logger.getLogger(FeatureController.class);

    @Autowired
    FeatureService featureService;

    @PostMapping()
    public ResponseEntity<HttpStatus> createFeature (@RequestBody Feature feature) {
        featureService.createFeature(feature);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<Collection<Feature>> readFeatures () {
        return ResponseEntity.ok(featureService.readFeatures());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feature> readFeature (@PathVariable Long id) throws ResourceNotFoundException {
        Feature feature = featureService.readFeature(id).orElseThrow(()->{
            log.error("Feature with id " + id + " not found");
            return new ResourceNotFoundException(String.format("Feature with id %s not found", id));
        });
        return ResponseEntity.ok(feature);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFeature (@PathVariable Long id) throws InvalidDataResource, ResourceNotFoundException {

        if(id <= 0) {
            throw new InvalidDataResource(String.format("Enter a valid id"));
        }

        Feature feature = featureService.readFeature(id).orElseThrow(()-> {
            log.error("Feature with id " + id + " not found");
            return new ResourceNotFoundException(String.format("Feature with id %s not found", id));
        });
        featureService.deleteFeature(id);
        return ResponseEntity.noContent().build();
    }
}
