package spr.service;

import spr.model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public User getUserById(Long id);

    public User getUserByEmail(String email);

    boolean addUser(String name, int age, String email, String password, String role);

    boolean addUser(User user);

    public boolean deleteUser(Long id);

    public boolean updateUser(Long id, String name, int age, String email, String password, String role);

    public boolean updateUser(User user);
}
