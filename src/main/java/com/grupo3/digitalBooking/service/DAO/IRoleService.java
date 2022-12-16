package com.grupo3.digitalBooking.service.DAO;

import com.grupo3.digitalBooking.model.Role;
import java.util.Collection;
import java.util.Optional;

public interface IRoleService {
    void createRole(Role role);
    Role updateRole (Role role);
    Collection<Role> readURoles ();
    Optional<Role> readRole (Long id);
    void deleteRole(Long id);
}
