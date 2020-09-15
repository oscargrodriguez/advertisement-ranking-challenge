package com.idealista.infrastructure.api;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.score.AdvertisementScorer;
import com.idealista.infrastructure.persistence.AdvertisementConverter;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AdsController {

    private InMemoryPersistence inMemoryPersistence;
    @Autowired
    private AdvertisementScorer advertisementScorer;

    public AdsController(InMemoryPersistence inMemoryPersistence) {
        this.inMemoryPersistence = inMemoryPersistence;
    }

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
        Optional<Advertisement> advertisement = inMemoryPersistence.findAdvertisement(advertisementId);
        if (advertisement.isPresent()) {
            int score = advertisementScorer.score(advertisement.get());
            return ResponseEntity.ok(score);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
