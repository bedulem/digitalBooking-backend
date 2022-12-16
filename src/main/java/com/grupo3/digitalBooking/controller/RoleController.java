package com.grupo3.digitalBooking.controller;

import com.grupo3.digitalBooking.exceptions.InvalidDataResource;
import com.grupo3.digitalBooking.exceptions.ResourceNotFoundException;
import com.grupo3.digitalBooking.model.Role;
import com.grupo3.digitalBooking.service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    final static Logger log = Logger.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;

    @PostMapping()
    public ResponseEntity<HttpStatus> createRole (@RequestBody Role role) {
        roleService.createRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<Collection<Role>> readRoles() {
        return  ResponseEntity.ok(roleService.readURoles());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRole (@PathVariable Long id) throws ResourceNotFoundException, InvalidDataResource {

        if(id <=0 ){
            throw new InvalidDataResource(String.format("Enter a valid id"));
        }

        Role role = roleService.readRole(id).orElseThrow(()-> {
            log.error("Role with id " + id + " not found");
            return new ResourceNotFoundException(String.format("Role with id %s not found", id));
        });

        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
