package spr.repository;

import spr.model.User;

import java.util.List;

public interface UserDAO {
    void saveUser(User user);

//    boolean addUser(String name, int age, String email, String password, String role);

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(User user);

    boolean deleteUserById(Long id);

    User getUserById(Long id);

    User getUserByEmail(String email);
    User getUserByName(String name);

    User getUserByNameAndPassword(String name, String password);

    List<User> getAllUsers();

}
