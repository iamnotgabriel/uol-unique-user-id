package org.fatec.uniqueuserid.users;

import com.fasterxml.jackson.annotation.JsonView;
import org.fatec.uniqueuserid.users.controller.dto.UserCreationDTO;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

    public static class UserData {}

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(User.UserData.class)
    private Integer id;

    @Column(name="user_name")
    @JsonView(User.UserData.class)
    private String name;

    @Column(name="user_email")
    @JsonView(User.UserData.class)
    private String email;

    @Column(name="user_phone")
    @JsonView(User.UserData.class)
    private String phone;

    @Column(name="user_password")
    private String password;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonView(User.UserData.class)
    SignUp signUp;

    public static User create(UserCreationDTO dto) {
        User user = new User();
        user.email = dto.email;
        user.name = dto.name;
        user.phone = dto.phone;
        user.password = dto.password;
        return user;
    }

    public User() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SignUp getSignUp() { return signUp; }
}
