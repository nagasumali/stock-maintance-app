package com.nagasumali.stocks.domain;

import com.nagasumali.stocks.makers.TradeMaker;
import com.natpryce.makeiteasy.MakeItEasy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.natpryce.makeiteasy.MakeItEasy.*;
import static org.testng.Assert.assertEquals;

public class TradeTest {

    private Trade victim;

    @BeforeMethod
    public void setupScenario() {
        victim = make(a(TradeMaker._ValidTrade,
                MakeItEasy.with(TradeMaker._stockSymbol, "XYZ"),
                with(TradeMaker._price, 1.0),
                with(TradeMaker._quantity, 2),
                with(TradeMaker._time, 123L),
                MakeItEasy.with(TradeMaker._tradeIndicator, TradeIndicator.BUY)
        ));
    }

    @Test
    public void shouldGetState() {
        assertEquals(victim.getStockSymbol(), "XYZ");
        assertEquals(victim.getPrice(), 1.0);
        assertEquals(victim.getQuantity(), Integer.valueOf(2));
        assertEquals(victim.getTime(), Long.valueOf(123));
        assertEquals(victim.getIndicator(), TradeIndicator.BUY);
    }

}
