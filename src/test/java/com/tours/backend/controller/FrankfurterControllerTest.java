package com.tours.backend.controller;
import com.tours.backend.domain.dtos.FrankfurterExchangeRatesDto;
import com.tours.backend.frankfurter.FrankfurterApiFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class FrankfurterControllerTest {

    private FrankfurterApiFacade frankfurterApiFacade;
    private FrankfurterController frankfurterController;

    @BeforeEach
    void setUp() {
        frankfurterApiFacade = mock(FrankfurterApiFacade.class);
        frankfurterController = new FrankfurterController(frankfurterApiFacade);
    }

    @Test
    void shouldReturnExchangeRatesDtoForGivenBaseCurrency() {
        // given
        String baseCurrency = "USD";
        FrankfurterExchangeRatesDto dto = new FrankfurterExchangeRatesDto();
        when(frankfurterApiFacade.getExchangeRate(baseCurrency)).thenReturn(dto);

        // when
        ResponseEntity<FrankfurterExchangeRatesDto> response = frankfurterController.getExchangeRate(baseCurrency);

        // then
        assertEquals(200, response.getStatusCodeValue());
        assertSame(dto, response.getBody());
        verify(frankfurterApiFacade, times(1)).getExchangeRate(baseCurrency);
    }
}