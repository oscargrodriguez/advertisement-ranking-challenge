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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class AdsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllScores() throws Exception {
        List<Integer> expectedResult = Arrays.asList(15, 100, 20, 100, 75, 50, 0, 45);
        mockMvc.perform(get("/score/getAllScores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(8)))
                .andExpect(jsonPath("$[0]", equalTo(expectedResult.get(0))))
                .andExpect(jsonPath("$[1]", equalTo(expectedResult.get(1))))
                .andExpect(jsonPath("$[2]", equalTo(expectedResult.get(2))))
                .andExpect(jsonPath("$[3]", equalTo(expectedResult.get(3))))
                .andExpect(jsonPath("$[4]", equalTo(expectedResult.get(4))))
                .andExpect(jsonPath("$[5]", equalTo(expectedResult.get(5))))
                .andExpect(jsonPath("$[6]", equalTo(expectedResult.get(6))))
                .andExpect(jsonPath("$[7]", equalTo(expectedResult.get(7))));
    }
}