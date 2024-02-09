package AhChacha.Backend.converter;

import AhChacha.Backend.domain.Platform;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Converter
@Slf4j
public class PlatformConverter implements AttributeConverter<Platform, String> {
    @Override
    public String convertToDatabaseColumn(Platform platform) {
        if (platform == null) return null;
        return platform.toString();
    }

    @Override
    public Platform convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        try {
            return Platform.GOOGLE;
        } catch (IllegalArgumentException e) {
            log.error("can't convert");
            throw e;
        }
    }
}
