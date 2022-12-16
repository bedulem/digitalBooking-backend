package com.grupo3.digitalBooking.service;

import com.grupo3.digitalBooking.model.Role;
import com.grupo3.digitalBooking.repository.RoleRepository;
import com.grupo3.digitalBooking.service.DAO.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void createRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        return null;
    }

    @Override
    public Collection<Role> readURoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> readRole(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role;
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
