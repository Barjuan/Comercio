package com.commerce.web;

import com.commerce.bean.PriceResponse;
import com.commerce.constants.Constants;
import com.commerce.service.CommerceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CommerceControllerTest {

    @InjectMocks
    private CommerceController commerceController;

    @Mock
    private CommerceService commerceService;

    @Test
    public void getPrices_OK() {
        when(commerceService.getPrices(anyInt(), anyString(), anyString())).thenReturn(PriceResponse.builder().price(25.45).build());

        PriceResponse response = commerceController.getPrices(35455, "ZARA", "2020-06-14 16:00:00");
        assertEquals(response.getPrice(), 25.45);
    }

    @Test(expected = ResponseStatusException.class)
    public void getPrices_KO_Not_Found() {
        when(commerceService.getPrices(anyInt(), anyString(), anyString())).thenThrow(new NoSuchElementException(Constants.PRICE_NOT_FOUND));

        commerceController.getPrices(35455, "ZARA", "2020-06-10 16:00:00");
    }

    @Test(expected = ResponseStatusException.class)
    public void getPrices_KO_Timestamp_Not_Valid() {
        when(commerceService.getPrices(anyInt(), anyString(), anyString())).thenThrow(new NoSuchElementException(Constants.TIMESTAMP_NOT_VALID));

        commerceController.getPrices(35455, "ZARA", "2020-06-55 16:00:00");
    }
}
