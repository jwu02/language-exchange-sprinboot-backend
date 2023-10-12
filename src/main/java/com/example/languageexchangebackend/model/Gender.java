package com.example.languageexchangebackend.model;

import com.fasterxml.jackson.annotation.JsonValue;

//// https://www.baeldung.com/jackson-serialize-enums#2-enum-as-a-json-object
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Gender {
    MALE("male"), FEMALE("female");

    private String gender;

    Gender (String gender) {
        this.gender = gender;
    }

    @JsonValue // way of controlling the marshaling output for an enum
    public String getGender() {
        return gender;
    }
}
