package com.grupo3.digitalBooking.service;

import com.grupo3.digitalBooking.model.Policy;
import com.grupo3.digitalBooking.repository.PolicyRepository;
import com.grupo3.digitalBooking.service.DAO.IPolicyService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PolicyService implements IPolicyService {

    private final PolicyRepository policyRepository;

    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public void createPolicy(Policy policy) {
        policyRepository.save(policy);
    }

    @Override
    public Policy updatePolicy(Policy policy) {
        return null;
    }

    @Override
    public Collection<Policy> readPolicies() {
        return policyRepository.findAll();
    }

    @Override
    public Optional<Policy> readPolicy(Long id) {
        return Optional.empty();
    }

    @Override
    public void deletePolicy(Long id) {

    }

    public void deletePolicyByProductID(Long id) {
        policyRepository.removeByProductId(id);
    }
}
