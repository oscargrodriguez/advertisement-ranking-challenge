package com.idealista.infrastructure.api;

import com.idealista.domain.model.advertisement.AdvertisementScored;
import com.idealista.domain.model.ports.primary.CalculateScoreUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ads/")
public class AdsController {

    @Autowired
    private CalculateScoreUseCase calculateScoreUseCase;
    @Autowired
    private AdConverter adConverter;

    @GetMapping("irrelevant")
    public ResponseEntity<List<QualityAd>> qualityListing() {
        return ResponseEntity.ok(toQualityAds(calculateScoreUseCase.getIrrelevantAds()));
    }

    @GetMapping("public")
    public ResponseEntity<List<PublicAd>> publicListing() {
        return ResponseEntity.ok(toPublicAds(calculateScoreUseCase.getPublicAdsOrderedByScoreDesc()));
    }

    @PutMapping("score-all")
    public ResponseEntity<List<QualityAd>> calculateScores() {
        return ResponseEntity.ok(toQualityAds(calculateScoreUseCase.scoreAll()));
    }

    @PutMapping("score/{id}")
    public ResponseEntity<QualityAd> calculateScore(@PathVariable int id) {
        return calculateScoreUseCase.score(id).
                map(ad -> adConverter.toQualityAd(ad)).
                map(ad -> ResponseEntity.ok(ad)).
                orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    private List<QualityAd> toQualityAds(List<AdvertisementScored> advertisementsScored) {
        return advertisementsScored.stream().map(ad -> adConverter.toQualityAd(ad)).collect(Collectors.toList());
    }

    private List<PublicAd> toPublicAds(List<AdvertisementScored> advertisementsScored) {
        return advertisementsScored.stream().map(ad -> adConverter.toPublicAd(ad)).collect(Collectors.toList());
    }
}
