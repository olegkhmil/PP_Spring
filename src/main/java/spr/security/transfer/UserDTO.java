package spr.security.transfer;

import spr.model.User;

public class UserDTO {
    private String name;
    private int age;
    private String email;

    public UserDTO() {
    }

    public UserDTO(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public UserDTO getUser(User user) {
        return new UserDTO(user.getName(), user.getAge(), user.getEmail());
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
}
