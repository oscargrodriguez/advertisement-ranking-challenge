package com.idealista.infrastructure.api;

import com.idealista.domain.model.ports.primary.CalculateScoreUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ads/")
public class AdsController {

    @Autowired
    private CalculateScoreUseCase calculateScoreUseCase;
    @Autowired
    private AdConverter adConverter;

    @GetMapping("irrelevant")
    public ResponseEntity<List<QualityAd>> qualityListing() {
        List<QualityAd> qualityAds = new ArrayList<>();
        calculateScoreUseCase.getIrrelevantAds().forEach(it -> qualityAds.add(adConverter.convertToQualityAd(it)));
        return ResponseEntity.ok(qualityAds);
    }

    @GetMapping("public")
    public ResponseEntity<List<PublicAd>> publicListing() {
        List<PublicAd> publicAds = new ArrayList<>();
        calculateScoreUseCase.getPublicAdsOrderedByScoreDesc().forEach(it -> publicAds.add(adConverter.convertToPublicAd(it)));
        return ResponseEntity.ok(publicAds);
    }

    @PutMapping("score-all")
    public ResponseEntity<List<QualityAd>> calculateScores() {
        List<QualityAd> qualityAds = new ArrayList<>();
        calculateScoreUseCase.scoreAll().forEach(it -> qualityAds.add(adConverter.convertToQualityAd(it)));
        return ResponseEntity.ok(qualityAds);
    }

    @PutMapping("score/{id}")
    public ResponseEntity<QualityAd> calculateScore(@PathVariable int id) {
        return calculateScoreUseCase.score(id).
                map(it-> adConverter.convertToQualityAd(it)).
                map(it -> ResponseEntity.ok(it)).
                orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
