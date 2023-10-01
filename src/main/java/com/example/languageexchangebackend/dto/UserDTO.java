package com.example.languageexchangebackend.dto;

import com.example.languageexchangebackend.model.Gender;

import java.time.LocalDate;

public class UserDTO {

    private long id;
    private String email;
    private String username;
    private String password;
    private LocalDate dob;
    private Gender gender;
    private String selfIntroduction;

    public UserDTO() {
    }

    public UserDTO(long id, String email, String username, String password, LocalDate dob, Gender gender, String selfIntroduction) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.selfIntroduction = selfIntroduction;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }
}
