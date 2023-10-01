package com.example.languageexchangebackend.response;

import com.example.languageexchangebackend.model.User;

public class LoginMessage {
    String message;
    Boolean status;
    User user;

    public LoginMessage(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public LoginMessage(String message, Boolean status, User user) {
        this.message = message;
        this.status = status;
        this.user = user;
    }

    // https://stackoverflow.com/questions/28466207/could-not-find-acceptable-representation-using-spring-boot-starter-web
    // careful with sending response objects back to client
    // need both getter and setter

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
