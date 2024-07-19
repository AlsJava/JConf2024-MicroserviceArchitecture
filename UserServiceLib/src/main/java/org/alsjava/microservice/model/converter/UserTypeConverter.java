package org.alsjava.microservice.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.alsjava.microservice.enums.UserType;

@Converter
public class UserTypeConverter implements AttributeConverter<UserType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserType attribute) {
        if (attribute == null) {
            return null;
        }
        return switch (attribute) {
            case DEVELOPER -> 0;
            case SUPPORT -> 1;
            case NORMAL -> 2;
        };
    }

    @Override
    public UserType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return switch (dbData) {
            case 0 -> UserType.DEVELOPER;
            case 1 -> UserType.SUPPORT;
            case 2 -> UserType.NORMAL;
            default -> null;
        };
    }
}
