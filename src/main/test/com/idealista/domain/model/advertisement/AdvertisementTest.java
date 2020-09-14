package com.idealista.domain.model.advertisement;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.idealista.domain.model.advertisement.AdvertisementType.CHALET;
import static com.idealista.domain.model.advertisement.AdvertisementType.HOUSE;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdvertisementTest {

    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }

    @Test
    public void emptyAdvertisement() {
        Advertisement advertisement = new Advertisement(HOUSE);
        verifyScore(0, advertisement.score());
    }

    @Test
    void onePhoto() {
        Advertisement advertisement = new Advertisement(HOUSE);
        advertisement.addPhotos(asList("AnyUri"));
        verifyScore(10, advertisement.score());
    }

    @Test
    void oneHighDefinitionPhoto() {
        Advertisement advertisement = new Advertisement(HOUSE);
        advertisement.addHighDefinitionPhotos(asList(("AnyUri")));
        verifyScore(20, advertisement.score());
    }

    @Test
    void severalPhotos() {
        Advertisement advertisement = new Advertisement(HOUSE);
        advertisement.addHighDefinitionPhotos(asList("AnyUriOne","AnyUriTwo"));
        advertisement.addPhotos(asList("AnyHdUriOne","AnyHdUriTwo"));
        verifyScore(60, advertisement.score());
    }

    @Test
    void shortHouseDescriptiveText() {
        Advertisement advertisement = new Advertisement(new DescriptiveText(generateRandomText(10)), HOUSE);
        verifyScore(5, advertisement.score());
        advertisement = new Advertisement(new DescriptiveText(generateRandomText(19)), HOUSE);
        verifyScore(5, advertisement.score());
    }

    @Test
    void mediumHouseDescriptiveText() {
        Advertisement advertisement = new Advertisement(new DescriptiveText(generateRandomText(30)), HOUSE);
        verifyScore(15, advertisement.score());
        advertisement = new Advertisement(new DescriptiveText(generateRandomText(20)), HOUSE);
        verifyScore(15, advertisement.score());
        advertisement = new Advertisement(new DescriptiveText(generateRandomText(49)), HOUSE);
        verifyScore(15, advertisement.score());
    }

    @Test
    void largeHouseDescriptiveText() {
        Advertisement advertisement = new Advertisement(new DescriptiveText(generateRandomText(80)), HOUSE);
        verifyScore(35, advertisement.score());
        advertisement = new Advertisement(new DescriptiveText(generateRandomText(50)), HOUSE);
        verifyScore(35, advertisement.score());
    }

    @Test
    void largeChaletDescriptiveText() {
        Advertisement advertisement = new Advertisement(new DescriptiveText(generateRandomText(80)), CHALET);
        verifyScore(25, advertisement.score());
        advertisement = new Advertisement(new DescriptiveText(generateRandomText(50)), CHALET);
        verifyScore(25, advertisement.score());
    }

    private String generateRandomText(int lenght)
    {
        String fakeText = "";
        Random random = new Random();
        String chars = "abcxyz";
        for (int i=0;i<lenght;i++)
        {
            fakeText += chars.charAt(random.nextInt(chars.length()));
        }
        return fakeText;
    }
}
