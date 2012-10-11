package com.novus.trader;

import com.novus.tradesim.*;
import java.util.*;

public class TradeAlgo extends ManagedAlgo {
	
	/** The training data for your algorithm. Maps a security id to a list of prices. */
    final Map<String, List<Double>> data = super.getTrainingData();

    /** Record last prices of each security. */
    private Map<String, Double> lastPrices;

    public TradeAlgo() {
        // Bootstrap last prices.
        lastPrices = new HashMap<String, Double>();
        for(Map.Entry<String, List<Double>> e : data.entrySet()) {
            List<Double> px = e.getValue();
            lastPrices.put(e.getKey(), px.get(px.size() - 1));
        }
    }

    public String getName() {
        return "Stupid ass cheap skate.";
    }

    public Map<String, TradeRequest> onMarketUpdate(TraderState state) {
        Double balance = state.balance();
        Map<String, Integer> positions = state.positions();
        Map<String, Double> securities = state.securities();

        Map<String, TradeRequest> orders = new HashMap<String, TradeRequest>();

        if(positions.isEmpty()) {
            // Buy the cheapest 2 securities.
            TradeRequest one = null, two = null;

            for(Map.Entry<String, Double> e: securities.entrySet()) {
                // Attempt to buy 2 at $5 under the ask.
                double willBid = e.getValue() - 5d;
                TradeRequest current = makeTradeRequest(2, willBid, 0, 0d);

                if(one == null) {
                    one = current;
                    orders.put(e.getKey(), current);
                } else if(two == null) {
                    two = current;
                    orders.put(e.getKey(), current);
                } else if(one.bidPrice() > willBid) {
                    one = current;
                    orders.put(e.getKey(), current);
                } else if(two.bidPrice() > willBid) {
                    two = current;
                    orders.put(e.getKey(), current);
                }
            }

            log(" Seed orders: " + orders);

        } else {
            // Buy anything that has decreased, sell anything that has increased.
            for(Map.Entry<String, Integer> pe : positions.entrySet()) {
                double currentPx = securities.get(pe.getKey());
                double previousPx = lastPrices.get(pe.getKey());

                if(currentPx > previousPx) // Sell!
                    orders.put(pe.getKey(), makeTradeRequest(0, 0d, pe.getValue(), currentPx + 5d));
                else if(currentPx < previousPx) // Buy!
                    orders.put(pe.getKey(), makeTradeRequest(5, currentPx - 5d, 0, 0d));
            }

            log("New orders: " + orders);
        }

        // Remember last prices.
        lastPrices = securities;

        return orders;
    }

    /**
     * Create a trade request.
     *
     * @param bidQuantity   The number of shares you wish to buy.
     * @param bidPrice      The price per share you are willing to pay.
     * @param askQuantity   The number of shares you wish to sell.
     * @param askPrice      The price per share you require.
     */
    public TradeRequest makeTradeRequest(int bidQuantity, double bidPrice, int askQuantity, double askPrice) {
        return new TradeRequest(bidQuantity, bidPrice, askQuantity, askPrice);
    }
  
}
