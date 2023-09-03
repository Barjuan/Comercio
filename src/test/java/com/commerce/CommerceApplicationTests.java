package com.commerce;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommerceApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getPriceTest1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/v1/commerce/prices?product_id=35455&brand=ZARA&periodValid=2020-06-14 10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startDate", is("2020-06-14 00:00:00")))
                .andExpect(jsonPath("$.endDate", is("2020-12-31 23:59:59")))
                .andExpect(jsonPath("$.priority", is(0)))
                .andExpect(jsonPath("$.productName", is("ZARA")))
                .andExpect(jsonPath("$.price", is(35.50)));
    }

    @Test
    public void getPriceTest2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/v1/commerce/prices?product_id=35455&brand=ZARA&periodValid=2020-06-14 16:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startDate", is("2020-06-14 15:00:00")))
                .andExpect(jsonPath("$.endDate", is("2020-06-14 18:30:00")))
                .andExpect(jsonPath("$.priority", is(1)))
                .andExpect(jsonPath("$.productName", is("ZARA")))
                .andExpect(jsonPath("$.price", is(25.45)));
    }

    @Test
    public void getPriceTest3() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/v1/commerce/prices?product_id=35455&brand=ZARA&periodValid=2020-06-14 21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startDate", is("2020-06-14 00:00:00")))
                .andExpect(jsonPath("$.endDate", is("2020-12-31 23:59:59")))
                .andExpect(jsonPath("$.priority", is(0)))
                .andExpect(jsonPath("$.productName", is("ZARA")))
                .andExpect(jsonPath("$.price", is(35.50)));
    }

    @Test
    public void getPriceTest4() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/v1/commerce/prices?product_id=35455&brand=ZARA&periodValid=2020-06-15 10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startDate", is("2020-06-15 00:00:00")))
                .andExpect(jsonPath("$.endDate", is("2020-06-15 11:00:00")))
                .andExpect(jsonPath("$.priority", is(1)))
                .andExpect(jsonPath("$.productName", is("ZARA")))
                .andExpect(jsonPath("$.price", is(30.50)));
    }

    @Test
    public void getPriceTest5() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/v1/commerce/prices?product_id=35455&brand=ZARA&periodValid=2020-06-16 21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startDate", is("2020-06-15 16:00:00")))
                .andExpect(jsonPath("$.endDate", is("2020-12-31 23:59:59")))
                .andExpect(jsonPath("$.priority", is(1)))
                .andExpect(jsonPath("$.productName", is("ZARA")))
                .andExpect(jsonPath("$.price", is(38.95)));
    }
}
