package com.idealista.infrastructure.api;

import com.idealista.Main;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class AdsControllerTest {
    public static final int IRRELEVANT_THRESHOLD = 40;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllScores() throws Exception {
        List<Integer> expectedIds = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> expectedScores = Arrays.asList(15, 100, 20, 100, 75, 50, 0, 45);
        mockMvc.perform(get("/getAllScores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(8)))
                .andExpect(jsonPath("$[0].score", equalTo(expectedScores.get(0))))
                .andExpect(jsonPath("$[0].id", equalTo(expectedIds.get(0))))
                .andExpect(jsonPath("$[1].score", equalTo(expectedScores.get(1))))
                .andExpect(jsonPath("$[1].id", equalTo(expectedIds.get(1))))
                .andExpect(jsonPath("$[2].score", equalTo(expectedScores.get(2))))
                .andExpect(jsonPath("$[2].id", equalTo(expectedIds.get(2))))
                .andExpect(jsonPath("$[3].score", equalTo(expectedScores.get(3))))
                .andExpect(jsonPath("$[3].id", equalTo(expectedIds.get(3))))
                .andExpect(jsonPath("$[4].score", equalTo(expectedScores.get(4))))
                .andExpect(jsonPath("$[4].id", equalTo(expectedIds.get(4))))
                .andExpect(jsonPath("$[5].score", equalTo(expectedScores.get(5))))
                .andExpect(jsonPath("$[5].id", equalTo(expectedIds.get(5))))
                .andExpect(jsonPath("$[6].score", equalTo(expectedScores.get(6))))
                .andExpect(jsonPath("$[6].id", equalTo(expectedIds.get(6))))
                .andExpect(jsonPath("$[7].score", equalTo(expectedScores.get(7))))
                .andExpect(jsonPath("$[7].id", equalTo(expectedIds.get(7))));
    }

    @Test
    void getPublicAds() throws Exception {
        mockMvc.perform(get("/getAllPublicAdvertisements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].id", equalTo(2)))
                .andExpect(jsonPath("$[1].id", equalTo(4)))
                .andExpect(jsonPath("$[2].id", equalTo(5)))
                .andExpect(jsonPath("$[3].id", equalTo(6)))
                .andExpect(jsonPath("$[4].id", equalTo(8)));
    }

    @Test
    void getIrrelevantAds() throws Exception {
        mockMvc.perform(get("/getAllIrrelevantAdvertisements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].id", lessThan(IRRELEVANT_THRESHOLD)))
                .andExpect(jsonPath("$[1].id", equalTo(3)))
                .andExpect(jsonPath("$[1].id", lessThan(IRRELEVANT_THRESHOLD)))
                .andExpect(jsonPath("$[2].id", equalTo(7)))
                .andExpect(jsonPath("$[2].id", lessThan(IRRELEVANT_THRESHOLD)));
    }
}