package com.idealista.infrastructure.api;

import com.idealista.Main;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
    public void chaletLongDescriptedWithoutPhotos() throws Exception {
        mockMvc.perform(get("/score/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("15")));
    }
}