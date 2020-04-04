package spr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spr.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    List<User> findAllByName(String name);
}
