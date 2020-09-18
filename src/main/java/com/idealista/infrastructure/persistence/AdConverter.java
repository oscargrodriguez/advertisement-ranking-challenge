package com.idealista.infrastructure.persistence;

import com.idealista.domain.model.advertisement.Advertisement;

import java.util.List;

public interface AdConverter {
    Advertisement convert(AdVO adVo);
}
