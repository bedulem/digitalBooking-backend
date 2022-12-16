package com.grupo3.digitalBooking.config;

import com.grupo3.digitalBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var rol = userRepository.findByEmail(username);

        if (rol != null) {
            User.UserBuilder userBuilder = User.withUsername(username);
            userBuilder.password(rol.getPassword()).roles(rol.getRole().getName());
            return userBuilder.build();
        } else {
            throw new UsernameNotFoundException(username);
        }

    }
}
