package com.example.languageexchangebackend.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

// https://www.baeldung.com/jpa-persisting-enums-in-jpa#converter
// allows us to convert an entity attribute/enum to a database value and vice versa
// this approach allows us to safely add new enum values
// or change existing ones without breaking already persisted data
@Converter(autoApply = true) // JPA will automatically apply the conversion logic to all mapped attributes of enum
public class LanguageProficiencyConverter implements AttributeConverter<LanguageProficiency, Integer> {

    @Override
    public Integer convertToDatabaseColumn(LanguageProficiency languageProficiency) {
        if (languageProficiency == null) {
            return null;
        }
        return languageProficiency.getProficiency();
    }

    @Override
    public LanguageProficiency convertToEntityAttribute(Integer proficiency) {
        if (proficiency == null) {
            return null;
        }

        return Stream.of(LanguageProficiency.values())
                .filter(p -> p.getProficiency() == proficiency)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
