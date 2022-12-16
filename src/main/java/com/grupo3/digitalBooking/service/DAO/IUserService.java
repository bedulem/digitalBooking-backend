package com.grupo3.digitalBooking.service.DAO;

import com.grupo3.digitalBooking.exceptions.ResourceNotFoundException;
import com.grupo3.digitalBooking.model.MyUser;
import com.grupo3.digitalBooking.model.MyUserDto;

import java.util.Collection;
import java.util.Optional;

public interface IUserService {
    void createUser(MyUserDto user) throws ResourceNotFoundException;
    MyUser updateUser (MyUser user, MyUserDto myUserDto) throws ResourceNotFoundException;
    Collection<MyUser> readUsers ();
    Optional<MyUser> readUser (Long id);
    void deleteUser(Long id);
}
