package spr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spr.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    @Query(value="select u from User u")
    List<User> readAllUsers();
}
