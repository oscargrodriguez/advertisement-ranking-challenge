package com.idealista.infrastructure.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdConverterFactory {
    @Autowired
    private AdConverter garageConverter;
    @Autowired
    private AdConverter flatConverter;
    @Autowired
    private AdConverter chaletConverter;

    public AdConverter getConverter(AdVO adVo) {
        if (adVo.isGarage()) {
            return garageConverter;
        }
        if (adVo.isFlat()) {
            return flatConverter;
        }
        return chaletConverter;
    }
}
