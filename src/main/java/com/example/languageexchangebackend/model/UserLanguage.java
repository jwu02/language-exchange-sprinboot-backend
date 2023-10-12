package com.example.languageexchangebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class UserLanguage {

    @Embeddable
    public static class Id implements Serializable {
        @Column(name = "user_id")
        private long userId;
        @Column(name = "language_id")
        private long languageId;

        public Id() {
        }

        public Id(long userId, long languageId) {
            this.userId = userId;
            this.languageId = languageId;
        }

        // implementing equals and hashcode is necessary for composite primary key
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return userId == id.userId && languageId == id.languageId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, languageId);
        }
    }

    @EmbeddedId
    private Id id = new Id();

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;

    // https://www.baeldung.com/hibernate-notnull-vs-nullable
    @NotNull // usually prefer @NotNull over nullable = false
    @Column(name = "proficiency")
    private LanguageProficiency proficiency;

    @NotNull
    @Column(name = "type")
    private UserLanguageType type;

    // many instances of this entity are mapped to one instance of another entity
    // creating a bidirectional relationship with User
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "language_id", insertable = false, updatable = false)
    @JsonManagedReference
    private Language language;

    public UserLanguage() {
    }

    public UserLanguage(LanguageProficiency proficiency, UserLanguageType type, User user, Language language) {
        this.proficiency = proficiency;
        this.type = type;
        this.user = user;
        this.language = language;
        this.id.userId = user.getId();
        this.id.languageId = language.getId();
        user.addUserLanguage(this);
        language.addUserLanguage(this);
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public LanguageProficiency getProficiency() {
        return proficiency;
    }

    public void setProficiency(LanguageProficiency proficiency) {
        this.proficiency = proficiency;
    }

    public UserLanguageType getType() {
        return type;
    }

    public void setType(UserLanguageType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLanguage that = (UserLanguage) o;
        return proficiency == that.proficiency && type == that.type && user.equals(that.user) && language.equals(that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proficiency, type, user, language);
    }
}
