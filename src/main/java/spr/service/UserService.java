package spr.service;

import spr.model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public User getUserById(Long id);

    public User getUserByEmail(String email);
    public User getUserByName(String name);

    boolean addUser(User user);

    public boolean deleteUser(Long id);

    public boolean updateUser(User user);
}
