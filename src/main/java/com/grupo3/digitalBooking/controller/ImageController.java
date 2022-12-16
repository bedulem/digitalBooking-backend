package com.grupo3.digitalBooking.controller;

import com.grupo3.digitalBooking.exceptions.InvalidDataResource;
import com.grupo3.digitalBooking.exceptions.ResourceNotFoundException;
import com.grupo3.digitalBooking.model.Image;
import com.grupo3.digitalBooking.service.ImageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    final static Logger log = Logger.getLogger(ImageController.class);

    @Autowired
    ImageService imageService;

    @PostMapping()
    public ResponseEntity<HttpStatus> createCity (@RequestBody Image image) {
        imageService.createImage(image);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<Collection<Image>> readImages () {
        return ResponseEntity.ok(imageService.readImages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> readImage (@PathVariable Long id) throws ResourceNotFoundException {
        Image image = imageService.readImage(id).orElseThrow(()->{
            log.error("Image with id " + id + " not found");
            return new ResourceNotFoundException(String.format("Image with id %s not found", id));
        });
        return ResponseEntity.ok(image);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteImage (@PathVariable Long id) throws InvalidDataResource, ResourceNotFoundException {

        if(id <= 0) {
            throw new InvalidDataResource(String.format("Enter a valid id"));
        }

        Image image = imageService.readImage(id).orElseThrow(()-> {
            log.error("Image with id " + id + " not found");
            return new ResourceNotFoundException(String.format("Image with id %s not found", id));
        });
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
}
