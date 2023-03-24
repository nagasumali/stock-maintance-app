package com.nagasumali.stocks.domain;

import com.nagasumali.stocks.makers.StockMaker;
import com.natpryce.makeiteasy.MakeItEasy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.natpryce.makeiteasy.MakeItEasy.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.testng.Assert.assertEquals;

public class StockTest {

    private Stock victim;

    @BeforeMethod
    public void setupScenario() {
        victim = make(a(StockMaker._ValidStock,
                MakeItEasy.with(StockMaker._symbol, "XYZ"),
                MakeItEasy.with(StockMaker._type, StockType.COMMON),
                with(StockMaker._lastDividend, 1.0),
                with(StockMaker._fixedDividend, 0.02),
                with(StockMaker._parValue, 3.0),
                with(StockMaker._tickerPrice, 4.0)
        ));
    }

    @Test
    public void shouldGetState() {
        assertEquals(victim.getSymbol(), "XYZ");
        assertEquals(victim.getLastDividend(), 1.0);
        assertEquals(victim.getFixedDividend(), 0.02);
        assertEquals(victim.getParValue(), 3.0);
        assertEquals(victim.getTickerPrice(), 4.0);
    }

    @Test
    public void shouldCalculateCommonDividendYield() {
        assertEquals(victim.getDividendYield(), 1.0 / 4.0);
    }

    @Test
    public void shouldCalculatePreferredDividendYield() {
        Stock victim = make(a(StockMaker._ValidStock,
                MakeItEasy.with(StockMaker._symbol, "XYZ"),
                MakeItEasy.with(StockMaker._type, StockType.PREFERRED),
                with(StockMaker._lastDividend, 1.0),
                with(StockMaker._fixedDividend, 0.02),
                with(StockMaker._parValue, 3.0),
                with(StockMaker._tickerPrice, 4.0)
        ));

        assertEquals(victim.getDividendYield(), 0.02 / 4.0);
    }

    @Test
    public void shouldCalculatePERatio() {

        Stock spied = spy(victim);
        doReturn(10.0).when(spied).getDividendYield();

        assertEquals(spied.getPERatio(), 4.0 / 10.0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldFailWithNullSymbol() {
        make(a(StockMaker._ValidStock,
                MakeItEasy.with(StockMaker._symbol, (String) null)
        ));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldFailWithEmptySymbol() {
        make(a(StockMaker._ValidStock,
                MakeItEasy.with(StockMaker._symbol, "")
        ));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldFailWithNullType() {
        make(a(StockMaker._ValidStock,
                MakeItEasy.with(StockMaker._type, (StockType) null)
        ));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldFailWithNegativeLastDividend() {
        make(a(StockMaker._ValidStock,
                with(StockMaker._lastDividend, -1.0)
        ));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldFailWithNegativeTickerPrice() {
        make(a(StockMaker._ValidStock,
                with(StockMaker._tickerPrice, -1.0)
        ));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldFailWithNegativeFixedDividend() {
        make(a(StockMaker._ValidStock,
                with(StockMaker._fixedDividend, -1.0)
        ));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldFailWithFixedDividendGreaterThanUnit() {
        make(a(StockMaker._ValidStock,
                with(StockMaker._fixedDividend, 1.1)
        ));
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldFailWithNegativeParValue() {
        make(a(StockMaker._ValidStock,
                with(StockMaker._parValue, -1.0)
        ));
    }

}
