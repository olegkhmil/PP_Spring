package spr.service;

import spr.model.Role;

public interface RoleService {
    java.util.List<Role> getAllRoles();

    Role getRoleById(Integer id);

    Role addRole(Role role);

    void deleteRole(Integer id);

    boolean updateRole(Role role);
}
