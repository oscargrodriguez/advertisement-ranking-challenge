package com.idealista.infrastructure.api;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.ports.primary.CalculateScoreUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AdsController {

    @Autowired
    private CalculateScoreUseCase calculateScoreUseCase;


    public static void main(String[] args) {
        SpringApplication.run(AdsController.class, args);
    }

    //TODO añade url del endpoint
    public ResponseEntity<List<QualityAd>> qualityListing() {
        //TODO rellena el cuerpo del método
        return ResponseEntity.notFound().build();
    }

    //TODO añade url del endpoint
    public ResponseEntity<List<PublicAd>> publicListing() {
        //TODO rellena el cuerpo del método
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/score/getAllScores")
    public ResponseEntity<List<Integer>> calculateScore() {
        List<Advertisement> advertisements = calculateScoreUseCase.scoreAll();
        return ResponseEntity.ok(advertisements.stream().map(Advertisement::getScore).collect(Collectors.toList()));
    }
}
