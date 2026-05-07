package com.guilhermeneto.crud_spring.enums.converters;

import com.guilhermeneto.crud_spring.enums.StatusEnum;
import java.util.stream.Stream;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<StatusEnum, String> {

    @Override
    public String convertToDatabaseColumn(StatusEnum status) {
        if (status == null) {
            return null;
        }
        return status.getValue();
    }

    @Override
    public StatusEnum convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return Stream.of(StatusEnum.values())
                .filter(c -> c.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
    
}
