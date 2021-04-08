package com.qcz.qmplatform.api.system.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

    private String username;

    private String password;

    private int age;

    public UserDto(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public UserDto() {
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
