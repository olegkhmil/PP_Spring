package spr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spr.model.Role;
import spr.repository.RoleDao;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

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
