package com.grupo3.digitalBooking.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo3.digitalBooking.exceptions.ResourceNotFoundException;
import com.grupo3.digitalBooking.model.MyUser;
import com.grupo3.digitalBooking.model.MyUserDto;
import com.grupo3.digitalBooking.model.Role;
import com.grupo3.digitalBooking.repository.ProductRepository;
import com.grupo3.digitalBooking.repository.RoleRepository;
import com.grupo3.digitalBooking.repository.UserRepository;
import com.grupo3.digitalBooking.service.DAO.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void createUser(MyUserDto userDto) throws ResourceNotFoundException {
        MyUser user = mapper.convertValue(userDto, MyUser.class);
        Role role= roleRepository.findById(1L).orElseThrow(()-> new ResourceNotFoundException(String.format("Role with id 1 not found")));
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public MyUser updateUser(MyUser user, MyUserDto myUserDto) throws ResourceNotFoundException {
        if(myUserDto.getName() !=null){
            user.setName(myUserDto.getName());
        }
        if(myUserDto.getLastName() !=null){
            user.setLastName(myUserDto.getLastName());
        }
        if(myUserDto.getEmail() !=null){
            user.setEmail(myUserDto.getEmail());
        }
        if(myUserDto.getCity() !=null){
            user.setCity(myUserDto.getCity());
        }
        if(myUserDto.getRole() !=null){
            Role role= roleRepository.findById(myUserDto.getRole().getId()).orElseThrow(()-> new ResourceNotFoundException(String.format("Role with id "+ myUserDto.getRole().getId() + " not found")));
            user.setRole(role);
        }

        return userRepository.save(user);
    }

    @Override
    public Collection<MyUser> readUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<MyUser> readUser(Long id) {
        Optional<MyUser> user = userRepository.findById(id);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public MyUser findByEmail (String email) {
        MyUser user = userRepository.findByEmail(email);
        return user;
    }
}
