package spr.repository;

import org.springframework.stereotype.Repository;
import spr.model.Role;

import javax.persistence.*;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean addRole(Role role) {
        try {
            Role role1 = getRoleByName(role.getRole_name());
            if (role1 == null) {
                entityManager.persist(role);
                return true;
            } else return false;
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException | NonUniqueResultException e) {
            return false;
        }
    }

    @Override
    public boolean updateRole(Role role) {
        try {
            entityManager.merge(role);
            return true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            return false;
        }
    }

    @Override
    public boolean deleteRole(Role role) {
        try {
            entityManager.remove(role);
            return true;
        } catch (TransactionRequiredException | IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Role getRoleById(Integer id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public boolean deleteRoleByID(Integer id) {
        Role role = getRoleById(id);
        if (role != null) {
            return deleteRole(role);
        }
        return false;
    }

    @Override
    public Role getRoleByName(String name) {
        try {
            return entityManager.createQuery("SELECT r FROM Role r WHERE r.role_name = :name", Role.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }
}
