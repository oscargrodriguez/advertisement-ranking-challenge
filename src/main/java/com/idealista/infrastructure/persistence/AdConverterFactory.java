package com.idealista.infrastructure.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdConverterFactory {
    @Autowired
    private AdConverter garageConverter;
    @Autowired
    private AdConverter flatConverter;
    @Autowired
    private AdConverter chaletConverter;

    public Optional<AdConverter> getConverter(AdVO adVo) {
        Optional<AdConverter> adConverter = Optional.empty();
        if (adVo.isGarage()) {
            adConverter = Optional.of(garageConverter);
        }
        if (adVo.isFlat()) {
            adConverter = Optional.of(flatConverter);
        } else if (adVo.isChalet()) {
            adConverter = Optional.of(chaletConverter);
        }
        return adConverter;
    }
}
