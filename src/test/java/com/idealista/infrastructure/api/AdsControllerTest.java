package com.idealista.infrastructure.api;

import com.idealista.Main;
import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class AdsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void advertisementIdNotFound() throws Exception {
        mockMvc.perform(get("/score/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void chaletLongDescriptedWithoutPhotos() throws Exception {
        mockMvc.perform(get("/score/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("15")));
    }

    @Test
    void flatFullyWithKeywords() throws Exception {
        mockMvc.perform(get("/score/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("100")));
    }


    @Test
    void garageWithoutPhotosWithOneKeyword() throws Exception {
        mockMvc.perform(get("/score/7"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("0")));
    }

    @Test
    void getAllScores() throws Exception {
        List<QualityAd> expectedResult = Arrays.asList();
        mockMvc.perform(get("/score/getAllScores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}