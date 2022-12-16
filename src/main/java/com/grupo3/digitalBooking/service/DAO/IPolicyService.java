package com.grupo3.digitalBooking.service.DAO;

import com.grupo3.digitalBooking.model.Policy;

import java.util.Collection;
import java.util.Optional;

public interface IPolicyService {

    void createPolicy(Policy policy);
    Policy updatePolicy (Policy policy);
    Collection<Policy> readPolicies ();
    Optional<Policy> readPolicy (Long id);
    void deletePolicy(Long id);
}
