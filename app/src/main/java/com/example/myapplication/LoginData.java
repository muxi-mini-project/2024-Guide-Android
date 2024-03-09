package com.example.myapplication;

public class LoginData { private String Login;
    private String Email;
    private String Password;
    private String token;

    public String getLogin() {
        return Login;
    }


    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getToken() {
        return token;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
