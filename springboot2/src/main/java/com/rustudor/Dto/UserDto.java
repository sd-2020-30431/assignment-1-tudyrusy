package com.rustudor.Dto;

public class UserDto {
    private String name;
    private String email;
    private int goal;

    public UserDto(String name, String email, int goal) {
        this.name = name;
        this.email = email;
        this.goal = goal;
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
}
