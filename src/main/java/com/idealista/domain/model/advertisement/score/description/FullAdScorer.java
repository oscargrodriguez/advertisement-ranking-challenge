package com.idealista.domain.model.advertisement.score.description;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.Typology;

public class FullAdScorer {
    public int score (Advertisement advertisement)
    {
        if (advertisement.getTypology().equals(Typology.GARAGE))
        {
            if (advertisement.hasPhoto())
            {
                return 40;
            }
        }
        return 0;
    }
}
