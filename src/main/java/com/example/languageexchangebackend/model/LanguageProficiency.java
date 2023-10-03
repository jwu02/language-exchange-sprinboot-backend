package com.example.languageexchangebackend.model;

// https://www.baeldung.com/jpa-persisting-enums-in-jpa#converter
public enum LanguageProficiency {
    BEGINNER(1), INTERMEDIATE(2), PROFICIENT(3), FLUENT(4), NATIVE(5);

    private int proficiency;

    LanguageProficiency(int proficiency) {
        this.proficiency = proficiency;
    }

    public int getProficiency() {
        return proficiency;
    }
}
