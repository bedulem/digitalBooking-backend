package com.grupo3.digitalBooking.service;

import com.grupo3.digitalBooking.model.Rule;
import com.grupo3.digitalBooking.repository.RuleRepository;
import com.grupo3.digitalBooking.service.DAO.IRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RuleService implements IRuleService {

    @Autowired
    RuleRepository ruleRepository;

    @Override
    public void createRule(Rule rule) {
        ruleRepository.save(rule);
    }

    @Override
    public Rule updateRule(Rule rule) {
        return null;
    }

    @Override
    public Collection<Rule> readURules() {
        return null;
    }

    @Override
    public Optional<Rule> readRule(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteRule(Long id) {

    }

    public void deleteRuleByProductID(Long id) {
        ruleRepository.removeByProductId(id);
    }
}
