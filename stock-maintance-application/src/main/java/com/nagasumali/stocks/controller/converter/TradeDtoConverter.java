package com.nagasumali.stocks.controller.converter;

import com.nagasumali.stocks.controller.api.TradeDto;
import com.nagasumali.stocks.domain.Trade;
import com.nagasumali.stocks.domain.TradeIndicator;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Converts a Trade DTO into a Trade from the internal domain.
 */
@Component
public class TradeDtoConverter implements Function<TradeDto, Trade> {
    @Override
    public Trade apply(TradeDto tradeDto) {
        double price = tradeDto.getPrice();
        int quantity = tradeDto.getQuantity();
        String stockSymbol = tradeDto.getStockSymbol();
        TradeIndicator indicator = TradeIndicator.valueOf(tradeDto.getIndicator());

        return new Trade(
                new DateTime(DateTimeZone.UTC).getMillis(),
                price, quantity, stockSymbol, indicator);
    }
}
