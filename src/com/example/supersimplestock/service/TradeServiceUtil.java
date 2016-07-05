package com.example.supersimplestock.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.supersimplestock.model.Stock;
import com.example.supersimplestock.model.Trade;

public class TradeServiceUtil {

	private static long counter = 1;
	private static List<Trade> trades = new ArrayList<Trade>();

	public static synchronized Trade addTrade(Stock stock, long timestamp, int shareQty, String buyer, String seller,
			BigDecimal price) {
		Trade t = new Trade(counter++, stock, timestamp, shareQty, buyer, seller, price);
		trades.add(t);
		return t;
	}

	public static List<Trade> findTrades() {
		return trades;
	}

	public static List<Trade> getLatestTrades(int minutes) {
		Calendar lastMinutes = Calendar.getInstance();
		lastMinutes.add(Calendar.MINUTE, -1 * minutes);

		List<Trade> toRet = new ArrayList<Trade>();

		for (Trade t : trades) {
			if (t.getTimestamp() > lastMinutes.getTimeInMillis()) {
				toRet.add(t);
			}
		}

		return toRet;
	}

	public static BigDecimal getStockPrice(List<Trade> trades) {

		BigDecimal toRet = new BigDecimal("0");
		try {

			BigDecimal divisor = new BigDecimal("0");
			for (Trade t : trades) {
				toRet = toRet.add(t.getPrice().multiply(new BigDecimal(t.getShareQty())));
				divisor = divisor.add(new BigDecimal(t.getShareQty()));
			}

			toRet = toRet.divide(divisor, 3, BigDecimal.ROUND_CEILING);
		} catch (Exception e) {
			System.err.println("Problem in calculating Stock price");
			throw e;
		}
		return toRet;
	}
}
