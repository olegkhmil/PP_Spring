package spr.service;

import spr.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByName(String name);

    boolean addUser(User user);

    void deleteUser(Long id);

    boolean updateUser(User user);
}
