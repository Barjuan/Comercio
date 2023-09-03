package com.commerce.service.impl;

import com.commerce.bean.PriceResponse;
import com.commerce.model.Prices;
import com.commerce.repository.PricesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CommerceServiceImplTest {

    @InjectMocks
    private CommerceServiceImpl commerceService;

    @Mock
    private PricesRepository pricesRepository;

    @Test
    public void getPrices_OK() {
        when(pricesRepository.findPricesByProductIdBrandDate(anyInt(), anyString(), any())).thenReturn(
                Arrays.asList(Prices.builder().price(25.45).productId(35455)
                        .priceList(1).brandId(1).curr("EUR").priority(1)
                        .endDate(Timestamp.valueOf("2020-06-13 15:01:01"))
                        .startDate(Timestamp.valueOf("2020-06-16 15:01:01")).build()));

        PriceResponse response = commerceService.getPrices(35455, "ZARA", "2020-06-14 15:01:01");

        assertEquals(response.getPrice(), 25.45);
    }

    @Test
    public void getPrices_OK_Priority() {
        when(pricesRepository.findPricesByProductIdBrandDate(anyInt(), anyString(), any())).thenReturn(
                Arrays.asList(Prices.builder().price(30.54).productId(35455)
                                .priceList(1).brandId(1).curr("EUR").priority(1)
                                .endDate(Timestamp.valueOf("2020-06-13 15:01:01"))
                                .startDate(Timestamp.valueOf("2020-06-16 15:01:01")).build(),
                        Prices.builder().price(25.45).productId(35455)
                                .priceList(2).brandId(0).curr("EUR").priority(1)
                                .endDate(Timestamp.valueOf("2020-06-13 15:01:01"))
                                .startDate(Timestamp.valueOf("2020-06-16 15:01:01")).build()));

        PriceResponse response = commerceService.getPrices(35455, "ZARA", "2020-06-14 15:01:01");

        assertEquals(response.getPrice(), 30.54);
    }

    @Test(expected = NoSuchElementException.class)
    public void getPrices_KO_Not_Found() {
        when(pricesRepository.findPricesByProductIdBrandDate(anyInt(), anyString(), any())).thenReturn(
                Arrays.asList());

        commerceService.getPrices(35455, "ZARA", "2020-06-14 15:01:01");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPrices_KO_Timestamp_Not_valid() {
        commerceService.getPrices(35455, "ZARA", "2020-06-55 15:01:01");
    }
}
