package com.grupo3.digitalBooking.service;

import com.grupo3.digitalBooking.model.HealthSecurity;
import com.grupo3.digitalBooking.repository.HealthSecurityRepository;
import com.grupo3.digitalBooking.service.DAO.IHealthSecurityService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class HealthSecurityService implements IHealthSecurityService {

     private final HealthSecurityRepository healthSecurityRepository;

    public HealthSecurityService(HealthSecurityRepository healthSecurityRepository) {
        this.healthSecurityRepository = healthSecurityRepository;
    }

    @Override
    public void createHealthSecurity(HealthSecurity healthSecurity) {
        healthSecurityRepository.save(healthSecurity);
    }

    @Override
    public HealthSecurity updateHealthSecurity(HealthSecurity healthSecurity) {
        return null;
    }

    @Override
    public Collection<HealthSecurity> readHealthSecurities() {
        return healthSecurityRepository.findAll();
    }

    @Override
    public Optional<HealthSecurity> readHealthSecurity(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteHealthSecurity(Long id) {

    }

    public void deleteHealthByProductID(Long id) {
        healthSecurityRepository.removeByProductId(id);
    }
}
