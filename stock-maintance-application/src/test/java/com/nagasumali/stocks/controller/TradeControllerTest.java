package com.nagasumali.stocks.controller;

import com.nagasumali.stocks.controller.api.TradeDto;
import com.nagasumali.stocks.controller.converter.TradeDtoConverter;
import com.nagasumali.stocks.domain.Trade;
import com.nagasumali.stocks.service.TradeService;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.nagasumali.stocks.makers.TradeDtoMaker._ValidTradeDto;
import static com.nagasumali.stocks.makers.TradeDtoMaker._stockSymbol;
import static com.nagasumali.stocks.makers.TradeMaker._ValidTrade;
import static com.natpryce.makeiteasy.MakeItEasy.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

public class TradeControllerTest {

    @Mock
    private TradeService tradeService;

    @Mock
    private TradeDtoConverter tradeDtoConverter;

    private TradeController victim;

    @BeforeMethod
    public void setupScenario() {
        initMocks(this);
        victim = new TradeController(tradeService, tradeDtoConverter);
    }

    @Test
    public void shouldPostTrade() {
        TradeDto tradeDto = make(a(_ValidTradeDto));
        Trade trade = make(a(_ValidTrade));

        when(tradeDtoConverter.apply(tradeDto)).thenReturn(trade);
        doNothing().when(tradeService).recordTrade(trade);

        victim.postTrade(tradeDto);

        verify(tradeDtoConverter).apply(tradeDto);
        verify(tradeService).recordTrade(trade);
    }

    @Test
    public void shouldReturnBadRequestResponseWhenMandatoryFieldsNotPresent() {
        String symbol = null;
        TradeDto tradeDto = make(a(_ValidTradeDto,
                with(_stockSymbol, symbol)
        ));

        ResponseEntity<String> response = victim.postTrade(tradeDto);
        assertEquals(response, ResponseEntity.badRequest().body("mandatory field not received"));
    }
}
