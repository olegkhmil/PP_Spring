package spr.service;

import spr.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);

    User getUserByName(String name);

    boolean addUser(User user);

    boolean deleteUser(Long id);

    boolean updateUser(User user);
}
