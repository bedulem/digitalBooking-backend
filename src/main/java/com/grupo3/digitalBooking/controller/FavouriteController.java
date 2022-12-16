package com.grupo3.digitalBooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo3.digitalBooking.exceptions.InvalidDataResource;
import com.grupo3.digitalBooking.exceptions.ResourceNotFoundException;
import com.grupo3.digitalBooking.model.*;
import com.grupo3.digitalBooking.service.FavouriteService;
import com.grupo3.digitalBooking.service.ProductService;
import com.grupo3.digitalBooking.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/favourites")
public class FavouriteController {

    final static Logger log = Logger.getLogger(FavouriteController.class);

    @Autowired
    FavouriteService favouriteService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    ObjectMapper mapper;


    @PostMapping()
    public ResponseEntity<HttpStatus> createFavourite (@RequestBody ProductDto product) throws ResourceNotFoundException, InvalidDataResource {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = userService.findByEmail(auth.getName());

        ProductBookingDto productAux = productService.readProduct(product.getId()).orElseThrow(()-> {
            log.error("Product with id " + product.getId() + " not found");
            return new ResourceNotFoundException(String.format("Product with id %s not found", product.getId()));
        });

         Product productAux2 = mapper.convertValue(product, Product.class);

        Optional<Favourite> favouriteAux = favouriteService.findByProductId(myUser.getId(), productAux2.getId());

        if (favouriteAux.isPresent()){
            throw new InvalidDataResource(String.format("The product already exists in favorites"));
        }

        Favourite favourite = new Favourite(null,myUser,productAux2);
        favouriteService.createFavourite(favourite);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collection<Product>> readFavouritesByUserId(@PathVariable Long id) throws InvalidDataResource {

        if(id <=0 ){
            throw new InvalidDataResource(String.format("Enter a valid id"));
        }
        return  ResponseEntity.ok(favouriteService.findByUserId(id));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<HttpStatus> deleteFavourite (@PathVariable Long productId) throws ResourceNotFoundException, InvalidDataResource {

        if(productId <=0 ){
            throw new InvalidDataResource(String.format("Enter a valid id"));
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = userService.findByEmail(auth.getName());

        Favourite favourite = favouriteService.findByProductId(myUser.getId(), productId).orElseThrow(()-> {
            log.error("Favourite with id " + productId + " not found");
            return new ResourceNotFoundException(String.format("Favourite with id %s not found", productId));
        });

        favouriteService.deleteFavourite(favourite.getId());
        return ResponseEntity.noContent().build();
    }

}
