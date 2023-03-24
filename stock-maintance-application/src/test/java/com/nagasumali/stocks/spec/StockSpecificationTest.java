package com.nagasumali.stocks.spec;

import com.nagasumali.stocks.domain.Stock;
import com.nagasumali.stocks.makers.StockMaker;
import com.natpryce.makeiteasy.MakeItEasy;
import org.testng.annotations.Test;

import static com.nagasumali.stocks.spec.StockSpecification.withSymbol;
import static com.natpryce.makeiteasy.MakeItEasy.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class StockSpecificationTest {

    @Test
    public void shouldValidateSymbol() {
        Stock stock1 = make(a(StockMaker._ValidStock, MakeItEasy.with(StockMaker._symbol, "FOO")));
        Stock stock2 = make(a(StockMaker._ValidStock, MakeItEasy.with(StockMaker._symbol, "BAR")));

        assertTrue(withSymbol("FOO").test(stock1));
        assertFalse(withSymbol("FOO").test(stock2));
    }

}
