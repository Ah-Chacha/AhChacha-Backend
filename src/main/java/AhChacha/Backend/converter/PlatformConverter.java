package AhChacha.Backend.converter;

import AhChacha.Backend.domain.Platform;
import AhChacha.Backend.exception.NotFoundException;
import AhChacha.Backend.exception.status.BaseExceptionResponseStatus;
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
            if(dbData.equals("GOOGLE")) {
                return Platform.GOOGLE;
            } else if (dbData.equals("KAKAO")) {
                return Platform.KAKAO;
            } else if (dbData.equals("NAVER")) {
                return Platform.NAVER;
            } else throw new NotFoundException(BaseExceptionResponseStatus.PLATFORM_NOT_FOUND);
        } catch (IllegalArgumentException e) {
            log.error("can't convert");
            throw e;
        }
    }
}
