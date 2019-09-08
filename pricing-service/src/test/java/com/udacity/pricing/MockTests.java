package com.udacity.pricing;


import com.udacity.pricing.domain.price.PriceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PriceRepository repository;

    @Before
    public void deleteAllBeforeTests() {
        repository.deleteAll();
    }

    @Test
    public void shouldReturnRepositoryIndex() throws Exception {
        mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$._links.prices").exists());
    }

    @Test
    public void shouldCreateEntity() throws Exception {
        mockMvc.perform(post("/prices").content(
                "{\"currency\": \"usd\", \"price\": 20000}")).andExpect(
                status().isCreated());
    }

    @Test
    public void getAllPrices() throws Exception {
        mockMvc.perform(get("/prices"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
