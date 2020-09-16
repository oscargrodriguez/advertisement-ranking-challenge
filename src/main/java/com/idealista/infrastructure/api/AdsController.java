package com.idealista.infrastructure.api;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.ports.primary.CalculateScoreUseCase;
import com.idealista.infrastructure.persistence.AdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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

    @GetMapping("/score/{advertisementId}")
    public ResponseEntity<Integer> calculateScore(@PathVariable int advertisementId) {
        return calculateScoreUseCase.score(advertisementId).
                map(score -> ResponseEntity.ok(score)).
                orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/score/getAllScores")
    public ResponseEntity<List<Integer>> calculateAllScores() {
        List<Advertisement> advertisements = calculateScoreUseCase.scoreAll();
        return ResponseEntity.ok(advertisements.stream().map(Advertisement::getScore).collect(Collectors.toList()));
    }
}
