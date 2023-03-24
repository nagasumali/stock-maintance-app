package com.nagasumali.stocks.service;

import com.nagasumali.stocks.domain.Trade;

/**
 * Trade service interface, that provides the operation for
 * storing trades in the underlying repository.
 */
public interface TradeService {

    /**
     * Records a trade into the underlying repository.
     * @param trade trade
     */
    void recordTrade(Trade trade);

}
