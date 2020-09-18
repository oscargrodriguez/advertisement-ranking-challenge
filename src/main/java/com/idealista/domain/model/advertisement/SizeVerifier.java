package com.idealista.domain.model.advertisement;

import org.springframework.stereotype.Component;

@Component
public class SizeVerifier {
    public boolean verify(Advertisement advertisement)
    {
        if (advertisement.isGarage())
        {
            return true;
        }
        else if (advertisement.isFlat())
        {
            return advertisement.hasHouseSize();
        }
        return advertisement.hasGardenSize() && advertisement.hasHouseSize();
    }
}
