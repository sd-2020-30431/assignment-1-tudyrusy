package com.rustudor.entity;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;



@Entity
@Table
public class Login implements Serializable {
    @Id
    @GeneratedValue
    @Column(nullable = false,updatable = false)
    private int id;
    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    private String password;
    @OneToOne(mappedBy = "login")
    @NotNull
    private User userFK;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserFK() {
        return userFK;
    }

    public void setUserFK(User userFK) {
        this.userFK = userFK;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userFK=" + userFK +
                ", role=" + role +
                '}';
    }
}
