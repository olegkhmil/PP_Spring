package spr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spr.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
        Role findRoleByName(String name);
}
