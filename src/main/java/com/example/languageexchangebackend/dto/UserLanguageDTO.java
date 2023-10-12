package com.example.languageexchangebackend.dto;

import com.example.languageexchangebackend.model.LanguageProficiency;

public class UserLanguageDTO {
    private long languageId;
    private LanguageProficiency proficiency;

    public UserLanguageDTO(int languageId, LanguageProficiency proficiency) {
        this.languageId = languageId;
        this.proficiency = proficiency;
    }

    public long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(long languageId) {
        this.languageId = languageId;
    }

    public LanguageProficiency getProficiency() {
        return proficiency;
    }

    public void setProficiency(LanguageProficiency proficiency) {
        this.proficiency = proficiency;
    }
}
