package com.example.languageexchangebackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "dob")
    private LocalDate dob;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    // https://www.baeldung.com/jpa-many-to-many
    // provide name of field in TeachLanguage which maps the relationship
    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    @JsonManagedReference
    private Set<UserLanguage> userLanguages = new HashSet<>();

    @Column(name = "self_introduction")
    private String selfIntroduction;

    public User() {
    }

    public User(long id, String email, String username, String password, LocalDate dob, Gender gender, String selfIntroduction) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.selfIntroduction = selfIntroduction;
    }

    public User(long id, String email, String username, String password, LocalDate dob, Gender gender, Set<UserLanguage> userLanguages, String selfIntroduction) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.userLanguages = userLanguages;
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

    public Set<UserLanguage> getUserLanguages() {
        return userLanguages;
    }

    public void setUserLanguages(Set<UserLanguage> userLanguages) {
        this.userLanguages = userLanguages;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public void addUserLanguage(UserLanguage userLanguage) {
        this.userLanguages.add(userLanguage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email) && username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, username);
    }
}
