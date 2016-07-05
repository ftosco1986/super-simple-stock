package com.example.supersimplestock.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.supersimplestock.model.Stock;

public class StockServiceUtil {

	private static long counter = 1;
	private static List<Stock> stocks = new ArrayList<Stock>();

	public static synchronized Stock addStock(String stockSymbol, int type, double lastDividend,
			double fixedDividendPercentage, double parValue) {

		Stock s = new Stock(counter++, stockSymbol, type, lastDividend, fixedDividendPercentage, parValue);
		stocks.add(s);
		return s;
	}

	public static List<Stock> findStocks() {
		return stocks;
	}

	public static BigDecimal getDividendYield(Stock stock, BigDecimal tickerPrice) throws Exception {

		BigDecimal toRet = new BigDecimal("0");
		if (stock != null && tickerPrice != null) {
			try {
				if (stock.getFixedDividendPercentage().doubleValue() > 0.0 && stock.getParValue().doubleValue() > 0.0) {
					toRet = stock.getFixedDividendPercentage().divide(new BigDecimal(100), 3, BigDecimal.ROUND_CEILING)
							.multiply(stock.getParValue()).divide(tickerPrice, 3, BigDecimal.ROUND_CEILING);
				} else if (stock.getLastDividend().doubleValue() > 0.0) {
					toRet = stock.getLastDividend().divide(tickerPrice, 3, BigDecimal.ROUND_CEILING);
				}
			} catch (Exception e) {
				System.err.println("Problem in calculating dividend yield for this stock: " + stock.toString());
				throw e;
			}
		}

		return toRet;
	}

	public static BigDecimal getPERatio(Stock stock, BigDecimal tickerPrice) throws ArithmeticException {

		BigDecimal toRet = new BigDecimal("0");
		if (stock != null && tickerPrice != null) {
			try {
				if (tickerPrice.doubleValue() > 0.0) {
					toRet = tickerPrice.divide(stock.getLastDividend(), 3, BigDecimal.ROUND_CEILING);
				}
			} catch (Exception e) {
				System.err.println("Problem in calculating P/E Ratio for this stock: " + stock.toString());
				throw e;
			}
		}

		return toRet;
	}

	public static BigDecimal gbceShareIndex(BigDecimal[] prices) {

		BigDecimal toRet = new BigDecimal("0");

		if (prices != null && prices.length > 0) {

			try {
				toRet = new BigDecimal("1.0");
				for (BigDecimal price : prices) {
					toRet = toRet.multiply(price);
				}

				toRet = new BigDecimal(Math.pow(toRet.doubleValue(), new BigDecimal(1)
						.divide(new BigDecimal(prices.length), 10, BigDecimal.ROUND_HALF_DOWN).doubleValue()));
			} catch (Exception e) {
				System.err.println("Problem in calculating price GBCE All Share Index");
				throw e;
			}
		}

		return toRet;
	}
}
