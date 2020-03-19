package spr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spr.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    java.util.List<User> findAllByName(String name);
}
