package AhChacha.Backend.converter;

import AhChacha.Backend.domain.Provider;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Converter
@Slf4j
public class ProviderConverter implements AttributeConverter<Provider, String> {
    @Override
    public String convertToDatabaseColumn(Provider provider) {
        if(provider == null) return null;
        return provider.toString();
    }

    @Override
    public Provider convertToEntityAttribute(String dbData) {
        if(dbData == null) return null;
        try {
            return Provider.ofLegacyCode(dbData);
        } catch (IllegalArgumentException e) {
            log.error("can't convert");
            throw e;
        }
    }
}
