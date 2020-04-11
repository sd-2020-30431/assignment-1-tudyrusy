package com.rustudor.entity;


import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table
public class User implements Serializable {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private int id;
    //@NotNull
    private String name;
    private int goal;
    private String email;
    //@NotNull
    @OneToMany(mappedBy = "userFK")
    private List<Item> GroceryList = new ArrayList<Item>();
    @OneToOne
    @NotNull
    private Login login;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public List<Item> getGroceryList() {
        return GroceryList;
    }

    public void setGroceryList(List<Item> groceryList) {
        GroceryList = groceryList;
    }
}
