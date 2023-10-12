package com.example.languageexchangebackend.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String>{
    @Override
    public String convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            return null;
        }
        return gender.getGender();
    }

    @Override
    public Gender convertToEntityAttribute(String gender) {
        if (gender == null) {
            return null;
        }

        return Stream.of(Gender.values())
                .filter(g -> g.getGender().equals(gender))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
