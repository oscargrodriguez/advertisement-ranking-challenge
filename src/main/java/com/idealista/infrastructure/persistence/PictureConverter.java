package com.idealista.infrastructure.persistence;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PictureConverter {
    public List<String> convert(List<PictureVO> pictures) {
        return pictures.stream().map(PictureVO::getUrl).collect(Collectors.toList());
    }
}
