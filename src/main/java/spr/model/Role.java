package spr.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String role_name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private java.util.Set<User> users;

    public Role() {
    }

    public Role(String role_name) {
        this.role_name = role_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String name) {
        this.role_name = name.toUpperCase();
    }

    public java.util.Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(role_name, role.role_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role_name);
    }

    @Override
    public String getAuthority() {
        return role_name;
    }

    @Override
    public String toString() {
        return role_name;
    }
}
