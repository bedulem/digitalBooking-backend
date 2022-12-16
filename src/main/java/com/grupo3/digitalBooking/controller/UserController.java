package com.grupo3.digitalBooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo3.digitalBooking.config.JwtUtilService;
import com.grupo3.digitalBooking.exceptions.InvalidDataResource;
import com.grupo3.digitalBooking.exceptions.ResourceNotFoundException;
import com.grupo3.digitalBooking.model.*;
import com.grupo3.digitalBooking.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    final static Logger log = Logger.getLogger(UserController.class);

     @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    ObjectMapper mapper;


    @PostMapping("/authenticate")
    public ResponseEntity<TokenInfo> authenticate(@RequestBody UserDto userDto) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(),
                            userDto.getPassword()));
        }catch (BadCredentialsException e) {
            throw new Exception("Incorrect", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(
                userDto.getEmail());

        final String jwt = jwtUtilService.generateToken(userDetails);
        MyUser myUser = userService.findByEmail(userDto.getEmail());
        TokenInfo tokenInfo = new TokenInfo(jwt,myUser.getId(),myUser.getName(),myUser.getLastName(), myUser.getEmail(),myUser.getCity(),myUser.getRole());

        return ResponseEntity.ok(tokenInfo);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createCategory (@RequestBody MyUserDto userDto) throws ResourceNotFoundException, InvalidDataResource {
        MyUser myUserAux = userService.findByEmail(userDto.getEmail());
        if(myUserAux != null) {
            throw  new InvalidDataResource(String.format("this mail already exists"));
        }else {
            userService.createUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @GetMapping()
    public ResponseEntity<Collection<MyUser>> readUsers () {
        return ResponseEntity.ok(userService.readUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MyUserDto> readUser (@PathVariable Long id) throws ResourceNotFoundException {
        MyUser user = userService.readUser(id).orElseThrow(() -> {
            log.error("User with id " + id + " not found");
            return new ResourceNotFoundException(String.format("User with id %s not found", id));
        });
        MyUserDto myUserDto = mapper.convertValue(user,MyUserDto.class);
        myUserDto.setPassword("****");
        return ResponseEntity.ok(myUserDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MyUser> updateUser (@PathVariable Long id, @RequestBody MyUserDto myUserDto) throws ResourceNotFoundException {
        MyUser user = userService.readUser(id).orElseThrow(()-> {
            log.error("User with id " + id + " not found");
            return new ResourceNotFoundException(String.format("User with id %s not found", id));
        });

        return ResponseEntity.ok(userService.updateUser(user, myUserDto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser (@PathVariable Long id) throws ResourceNotFoundException, InvalidDataResource {

        if(id <=0 ){
            throw new InvalidDataResource(String.format("Enter a valid id"));
        }

        MyUser user = userService.readUser(id).orElseThrow(()-> {
            log.error("User with id " + id + " not found");
            return new ResourceNotFoundException(String.format("User with id %s not found", id));
        });

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
