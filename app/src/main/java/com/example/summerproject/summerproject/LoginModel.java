package com.example.summerproject.summerproject;

public class LoginModel {

    //this way to access firebase require public data and default constuctor
public String username,password ;

    public LoginModel() {
    }

    public LoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
