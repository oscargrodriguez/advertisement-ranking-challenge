package com.idealista.domain.score.description;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.idealista.domain.model.advertisement.Advertisement.isChalet;
import static com.idealista.domain.model.advertisement.Advertisement.isFlat;
import static com.idealista.domain.score.description.Length.*;

@Component
@ConfigurationProperties(prefix = "score.description.length")
public class LengthScorer {

    @Autowired
    private LengthCalculator lengthCalculator;

    private Map<Length,Integer> flat;
    private Map<Length,Integer> chalet;
    private Map<Length,Integer> garage;

    public void setFlat(Map<Length, Integer> flat) {
        this.flat = flat;
    }

    public void setChalet(Map<Length, Integer> chalet) {
        this.chalet = chalet;
    }

    public void setGarage(Map<Length, Integer> garage) {
        this.garage = garage;
    }

    public int score(Advertisement advertisement) {
        Length length = lengthCalculator.get(advertisement.getDescription());
        if (isFlat().test(advertisement)) {
            return flat.get(length);
        } else if (isChalet().test(advertisement)) {
            return chalet.get(length);
        }
        return garage.get(length);
    }
}
