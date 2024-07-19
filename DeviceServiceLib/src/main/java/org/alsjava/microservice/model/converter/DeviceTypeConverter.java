package org.alsjava.microservice.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.alsjava.microservice.enums.DeviceType;

@Converter
public class DeviceTypeConverter implements AttributeConverter<DeviceType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DeviceType attribute) {
        if (attribute == null) {
            return null;
        }
        return switch (attribute) {
            case PC_DESKTOP -> 0;
            case PHONE -> 1;
            case LAPTOP -> 2;
            case TABLET -> 3;
        };
    }

    @Override
    public DeviceType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return switch (dbData) {
            case 0 -> DeviceType.PC_DESKTOP;
            case 1 -> DeviceType.PHONE;
            case 2 -> DeviceType.LAPTOP;
            case 3 -> DeviceType.TABLET;
            default -> null;
        };
    }
}
