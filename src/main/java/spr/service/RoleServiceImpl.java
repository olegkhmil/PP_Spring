package spr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spr.model.Role;
import spr.repository.RoleDao;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    public boolean addRole(Role role) {
        return roleDao.addRole(role);
    }

    @Override
    public boolean deleteRole(Integer id) {
        return roleDao.deleteRoleByID(id);
    }

    @Override
    public boolean updateRole(Role role) {
        return false;
    }
}
