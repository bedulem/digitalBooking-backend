package com.grupo3.digitalBooking.controller;

import com.grupo3.digitalBooking.exceptions.InvalidDataResource;
import com.grupo3.digitalBooking.exceptions.ResourceNotFoundException;
import com.grupo3.digitalBooking.model.Category;
import com.grupo3.digitalBooking.model.CategoryDto;
import com.grupo3.digitalBooking.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    final static Logger log = Logger.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<HttpStatus> createCategory (@RequestBody Category category) {
        categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<Collection<Category>> readCategories () {
        return ResponseEntity.ok(categoryService.readCategories());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory (@PathVariable Long id, @RequestBody CategoryDto categoryDto) throws ResourceNotFoundException {

        Category category = categoryService.readCategory(id).orElseThrow(()-> {
            log.error("Category with id " + id + " not found");
            return new ResourceNotFoundException(String.format("Category with id %s not found", id));
        });

        categoryService.updateCategory(category, categoryDto);
        return ResponseEntity.ok(category);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCategory (@PathVariable Long id) throws ResourceNotFoundException, InvalidDataResource {

        if(id <=0 ){
            throw new InvalidDataResource(String.format("Enter a valid id"));
        }

        Category category = categoryService.readCategory(id).orElseThrow(()-> {
            log.error("Category with id " + id + " not found");
            return new ResourceNotFoundException(String.format("Category with id %s not found", id));
        });

        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
