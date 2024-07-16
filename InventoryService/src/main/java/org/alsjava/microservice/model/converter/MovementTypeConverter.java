package org.alsjava.microservice.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.alsjava.microservice.model.MovementType;

@Converter
public class MovementTypeConverter implements AttributeConverter<MovementType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(MovementType attribute) {
        if (attribute == null) {
            return null;
        }
        return switch (attribute) {
            case QUOTE -> 0;
            case SELL -> 1;
        };
    }

    @Override
    public MovementType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return switch (dbData) {
            case 0 -> MovementType.QUOTE;
            case 1 -> MovementType.SELL;
            default -> null;
        };
    }
}
