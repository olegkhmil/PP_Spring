package spr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spr.model.Role;
import spr.repository.RoleRepository;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.getOne(id);
    }


    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Integer id) {
         roleRepository.deleteById(id);
    }

    @Override
    public boolean updateRole(Role role) {
        return false;
    }
}
