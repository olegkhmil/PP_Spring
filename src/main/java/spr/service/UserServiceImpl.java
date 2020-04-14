package spr.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spr.model.User;
import spr.repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.readAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public boolean addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepository.findByName(user.getName()) == null) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean updateUser(User user) {
        User user1 = userRepository.findByName(user.getName());
        if(user1 == null || user1.getId().equals(user.getId())) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

}
