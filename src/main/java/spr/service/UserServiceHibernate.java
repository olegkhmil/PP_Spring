package spr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spr.repository.UserDAO;

import spr.model.User;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

@Service
@Transactional
public class UserServiceHibernate implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceHibernate(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public boolean addUser(String name, int age, String email, String password, String role) {
        try {
            if (getUserByEmail(email) == null) {
                return userDAO.addUser(name, age, email, password, role);
            } else {
                return false;
            }
        } catch (NonUniqueResultException e) {
            return false;
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        return userDAO.deleteUserById(id);
    }

    @Override
    public boolean updateUser(Long id, String name, int age, String email, String password, String role) {
        User user = userDAO.getUserById(id);
        if (user != null &&
                (userDAO.getUserByEmail(email) == null || userDAO.getUserByEmail(email).getId().equals(id))) {
            user.setName(name);
            user.setAge(age);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);
            userDAO.updateUser(user);
            return true;
        } else return false;
    }

}
