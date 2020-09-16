package com.idealista.infrastructure.api;

import com.idealista.domain.model.ports.primary.CalculateScoreUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdsController {

    @Autowired
    private CalculateScoreUseCase calculateScoreUseCase;
    @Autowired
    private AdvertisementApiConverter advertisementApiConverter;

    public static void main(String[] args) {
        SpringApplication.run(AdsController.class, args);
    }

    @GetMapping("/score/getAllIrrelevantAdvertisements")
    public ResponseEntity<List<QualityAd>> qualityListing() {
        List<QualityAd> qualityAds = new ArrayList<>();
        calculateScoreUseCase.getAllIrrelevantAds().forEach(it -> qualityAds.add(advertisementApiConverter.convertToQualityAd(it)));
        return ResponseEntity.ok(qualityAds);
    }

    @GetMapping("/score/getAllPublicAdvertisements")
    public ResponseEntity<List<PublicAd>> publicListing() {
        List<PublicAd> publicAds = new ArrayList<>();
        calculateScoreUseCase.getAllPublicAdsOrderedByRankingDesc().forEach(it -> publicAds.add(advertisementApiConverter.convertToPublicAd(it)));
        return ResponseEntity.ok(publicAds);
    }

    @GetMapping("/score/getAllScores")
    public ResponseEntity<List<QualityAd>> calculateScore() {
        List<QualityAd> qualityAds = new ArrayList<>();
        calculateScoreUseCase.scoreAll().forEach(it -> qualityAds.add(advertisementApiConverter.convertToQualityAd(it)));
        return ResponseEntity.ok(qualityAds);
    }
}
