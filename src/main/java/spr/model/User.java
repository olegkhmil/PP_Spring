package spr.model;

import javax.persistence.*;
import java.util.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;
    @Column(name = "hash_password")
    private String hash_password;
    @Transient
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private java.util.Set<Role> roles;

    @Column(name = "state")
    @Enumerated(value = EnumType.STRING)
    private State state;

    public User() {
    }

    public User(Long id, String name, int age, String email, String hash_password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.hash_password = hash_password;
    }

    public User(String name, int age, String email, String hash_password, java.util.Set<Role> roles, State state) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.hash_password = hash_password;
        this.roles = roles;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getHash_password() {
        return hash_password;
    }

    public void setHash_password(String hash_password) {
        this.hash_password = hash_password;
    }

    public Set<Role> getRoles() {
        if (roles != null)
            return roles;
        return new HashSet<>();
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
