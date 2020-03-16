package spr.repository;

import spr.model.Role;

import java.util.List;

public interface RoleDao {
    boolean addRole(Role role);

    boolean updateRole(Role role);

    boolean deleteRole(Role role);

    boolean deleteRoleByID(Integer id);

    Role getRoleById(Integer id);

    Role getRoleByName(String name);

    List<Role> getAllRoles();
}
