package spr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spr.repository.UserDAO;

import spr.model.User;

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
        return userDAO.addUser(name, age, email, password, role);
    }

    @Override
    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userDAO.deleteUserById(id);
    }

    @Override
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
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
