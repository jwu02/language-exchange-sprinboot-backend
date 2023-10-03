package com.example.languageexchangebackend.dto;

public class LanguageDTO {
    private long id;
    private String name;
    private String code;
    private String nameInLanguage;

    public LanguageDTO(long id, String name, String code, String nameInLanguage) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.nameInLanguage = nameInLanguage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameInLanguage() {
        return nameInLanguage;
    }

    public void setNameInLanguage(String nameInLanguage) {
        this.nameInLanguage = nameInLanguage;
    }
}
