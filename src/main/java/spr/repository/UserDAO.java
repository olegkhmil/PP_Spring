package spr.repository;

import spr.model.Role;
import spr.model.State;
import spr.model.User;

import java.util.List;
import java.util.Set;

public interface UserDAO {
    void saveUser(User user);

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(User user);

    boolean deleteUserById(Long id);

    User getUserById(Long id);

    User getUserByEmail(String email);

    User getUserByName(String name);

    List<User> getAllUsers();

}
