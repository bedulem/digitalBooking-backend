package com.grupo3.digitalBooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/categories/**").permitAll()
                .antMatchers("/api/v1/cities/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/products/").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/v1/products").hasRole("ADMIN")
                .antMatchers("/api/v1/products/**").permitAll()
                .antMatchers("/api/v1/images/**").permitAll()
                .antMatchers("/api/v1/features/**").permitAll()
                .antMatchers("/api/v1/roles/**").permitAll()
                .antMatchers("/api/v1/users/**").permitAll()
                .antMatchers("/api/v1/users/authenticate/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/bookings/").hasRole("CLIENT")
                .antMatchers(HttpMethod.POST,"/api/v1/bookings").hasRole("CLIENT")
                .antMatchers(HttpMethod.GET,"/api/v1/favourites/**").permitAll()
                .antMatchers("/api/v1/favourites").hasRole("CLIENT")
                .antMatchers("/api/v1/bookings/**").permitAll()
                .anyRequest().authenticated()
                .and().cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);

    }
}
