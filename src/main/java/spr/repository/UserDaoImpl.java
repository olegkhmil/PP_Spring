package spr.repository;

import org.springframework.stereotype.Repository;
import spr.model.User;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public boolean addUser(String name, int age, String email, String password, String role) {
        try {
            User user = getUserByEmail(email);
            if (user == null)
                entityManager.persist(new User(name, age, email, password, role));
            return true;
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException | NonUniqueResultException e) {
            return false;
        }
    }

    @Override
    public boolean addUser(User user) {
        try {
            User user1 = getUserByEmail(user.getEmail());
            if (user1 == null)
                entityManager.persist(user);
            return true;
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException | NonUniqueResultException e) {
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try {
            entityManager.merge(user);
            return true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            return false;
        }
    }

    @Override
    public boolean deleteUser(User user) {
        try {
            entityManager.remove(user);
            return true;
        } catch (TransactionRequiredException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean deleteUserById(Long id) {
        User user = getUserById(id);
        if (user != null) {
            return deleteUser(user);
        }
        return false;
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        User user;
        try {
            user = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }


}
