package com.udacity.pricing;

import com.udacity.pricing.domain.price.PriceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PriceRepository repository;

    @Before
    public void deleteAllBeforeTests() throws Exception {
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
    // to reviewer:
    // I don't know how to populate prices.json file so no further andExpect() chain is called.
    public void getAllPrices() throws Exception {
        mockMvc.perform(get("/prices"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
