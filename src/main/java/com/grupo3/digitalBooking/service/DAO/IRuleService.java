package com.grupo3.digitalBooking.service.DAO;

import com.grupo3.digitalBooking.model.Rule;

import java.util.Collection;
import java.util.Optional;

public interface IRuleService {
    void createRule(Rule rule);
    Rule updateRule (Rule rule);
    Collection<Rule> readURules ();
    Optional<Rule> readRule (Long id);
    void deleteRule(Long id);
}
