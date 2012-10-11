package com.novus.trader;

import com.novus.tradesim.*;
import java.util.List;
import java.util.Map;

public class TradeAlgo extends ManagedAlgo {
	
	/**
	 * The training data for your algorithm. Maps a security id to a list of prices. 
	 */
    final Map<String, List<Double>> data = super.getTrainingData();

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
    public Map<String, TradeRequest> onMarketUpdate(TraderState state) {
        Double balance = state.balance();
        Map<String, Integer> positions = state.positions();
        Map<String, Double> securities = state.securities();

        // TODO your algorithm!

        return null;
    }
    
    /**
     * 
     * TradeRequest is constructed as: 
     * 		new TradeRequest(
     * 			   int bidQuantity, 
     * 			   double bidPrice,
     *             int askQuantity,
     *             double askPrice)
     * 
     */
  
}
