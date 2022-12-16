package com.grupo3.digitalBooking.controller;

import com.grupo3.digitalBooking.exceptions.InvalidDataResource;
import com.grupo3.digitalBooking.exceptions.ResourceNotFoundException;
import com.grupo3.digitalBooking.model.*;
import com.grupo3.digitalBooking.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    final static Logger log = Logger.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @PostMapping()
    public ResponseEntity<HttpStatus> createCategory (@RequestBody ProductDto productDto) {

        productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

        @GetMapping()
    public ResponseEntity<Collection<Product>> readProducts (@RequestParam(value = "city", required = false) Long cityId,
                                                             @RequestParam(value = "category", required = false) Long categoryId,
                                                             @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
                                                             @RequestParam(value = "limit", required = false, defaultValue = "16") int limit,
                                                             @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                                             @RequestParam(value = "checkIn",required = false) String checkIn,
                                                             @RequestParam(value = "checkOut",required = false) String checkOut) throws InvalidDataResource {

        if(checkIn != null ){
            LocalDate checkInAux = LocalDate.parse(checkIn);
            LocalDate checkOutAux = LocalDate.parse(checkOut);
            if(cityId == null) {
                throw new InvalidDataResource("The city is missing");
            }
            if(checkInAux.equals(checkOutAux)) {
                throw new InvalidDataResource("The check in date is the same as the checkout date");
            }
            if(checkInAux.isAfter(checkOutAux)) {
                throw new InvalidDataResource("The check in date cannot be after the checkout date");
            }
            if(categoryId == null) {
                return ResponseEntity.ok(productService.filterByCityAndDates(cityId, checkInAux, checkOutAux));
            }
            return ResponseEntity.ok(productService.filterByCityAndDatesAndCategory(cityId,checkInAux,checkOutAux,categoryId));
        }
         return ResponseEntity.ok(productService.readProductsPaginated( cityId,categoryId,order, limit, offset));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductBookingDto> readProduct (@PathVariable Long id) throws ResourceNotFoundException {
        ProductBookingDto product = productService.readProduct(id).orElseThrow(() -> {
            log.error("Product with id " + id + " not found");
            return new ResourceNotFoundException(String.format("Product with id %s not found", id));
        });
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateCategory (@PathVariable Long id, @RequestBody ProductDto productDto) throws ResourceNotFoundException {

        Product product = productService.readSimpleProduct(id).orElseThrow(()-> {
            log.error("Product with id " + id + " not found");
            return new ResourceNotFoundException(String.format("Product with id %s not found", id));
        });

        productService.updateProduct(product, productDto);
        return ResponseEntity.ok(product);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct (@PathVariable Long id) throws ResourceNotFoundException, InvalidDataResource {

        if(id <= 0) {
            throw new InvalidDataResource(String.format("Enter a valid id"));
        }

        ProductBookingDto product = productService.readProduct(id).orElseThrow(() -> {
            log.error("Product with id " + id + " not found");
            return new ResourceNotFoundException(String.format("Product with id %s not found", id));
        });
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
