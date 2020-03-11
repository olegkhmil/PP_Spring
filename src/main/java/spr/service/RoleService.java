package spr.service;

import spr.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRoleById(Integer id);

    Role getRoleByName(String name);

    boolean addRole(Role role);

    boolean deleteRole(Integer id);

    boolean updateRole(Role role);
}
