package com.novus.trader;

import com.novus.tradesim.*;
import java.util.Map;

public class TradeAlgo extends ManagedAlgo {

    /** The name of your algorithm. */
    public String getName() {
        return "Trends";
    }

    /**
     * Your algorithm implementation resides here. This method is called by the provided
     * client when a market event occurs. Such an event results in a new state notification
     * from the server, which includes your current balance, positions, and the current
     * prices of the market securities.
     */
    public Map<String, BidAsk> onMarketUpdate(TraderState state) {
        Double balance = state.balance();
        Map<String, Integer> positions = state.positions();
        Map<String, Double> securities = state.securities();

        // TODO magic

        return null;
    }

}
