package com.grupo3.digitalBooking.service.DAO;

import com.grupo3.digitalBooking.model.HealthSecurity;

import java.util.Collection;
import java.util.Optional;

public interface IHealthSecurityService {

    void createHealthSecurity(HealthSecurity healthSecurity);
    HealthSecurity updateHealthSecurity (HealthSecurity healthSecurity);
    Collection<HealthSecurity> readHealthSecurities ();
    Optional<HealthSecurity> readHealthSecurity (Long id);
    void deleteHealthSecurity(Long id);
}
