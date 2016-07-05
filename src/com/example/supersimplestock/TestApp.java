package com.example.supersimplestock;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.example.supersimplestock.model.Stock;
import com.example.supersimplestock.model.Trade;
import com.example.supersimplestock.service.StockServiceUtil;
import com.example.supersimplestock.service.TradeServiceUtil;

public class TestApp {

	public static void main(String[] args) {

		Stock s1 = StockServiceUtil.addStock("TEA", StockConstants.STOCK_TYPE_COMMON, 0.0, 0.0, 100.0);
		Stock s2 = StockServiceUtil.addStock("POP", StockConstants.STOCK_TYPE_COMMON, 8.0, 0.0, 100.0);
		Stock s3 = StockServiceUtil.addStock("ALE", StockConstants.STOCK_TYPE_COMMON, 23.0, 0.0, 60.0);
		Stock s4 = StockServiceUtil.addStock("GIN", StockConstants.STOCK_TYPE_PREFERRED, 8.0, 2.0, 100.0);
		Stock s5 = StockServiceUtil.addStock("JOE", StockConstants.STOCK_TYPE_COMMON, 13.0, 0.0, 250.0);

		Calendar now = Calendar.getInstance();

		Random qtyRandom = new Random(100);
		Random priceRandom = new Random(100000);
		for (int minutes = 0; minutes < 30; minutes++) {

			now.add(Calendar.MINUTE, (-1) * minutes);
			TradeServiceUtil.addTrade(s1, now.getTimeInMillis(), qtyRandom.nextInt(100), "Stock1 buyer",
					"Stock1 seller", new BigDecimal(priceRandom.nextInt(100000)));

			TradeServiceUtil.addTrade(s2, now.getTimeInMillis(), qtyRandom.nextInt(100), "Stock2 buyer",
					"Stock2 seller", new BigDecimal(priceRandom.nextInt(100000)));
			TradeServiceUtil.addTrade(s3, now.getTimeInMillis(), qtyRandom.nextInt(100), "Stock3 buyer",
					"Stock3 seller", new BigDecimal(priceRandom.nextInt(100000)));

			TradeServiceUtil.addTrade(s4, now.getTimeInMillis(), qtyRandom.nextInt(100), "Stock4 buyer",
					"Stock4 seller", new BigDecimal(priceRandom.nextInt(100000)));

			TradeServiceUtil.addTrade(s5, now.getTimeInMillis(), qtyRandom.nextInt(100), "Stock5 buyer",
					"Stock5 seller", new BigDecimal(priceRandom.nextInt(100000)));
		}

		List<Stock> stocks = StockServiceUtil.findStocks();
		List<Trade> trades = TradeServiceUtil.findTrades();

		System.out.println("Super simple stocks example: \n");
		for (Stock stock : stocks) {
			System.out.println(stock.toString());
		}

		System.out.println("\n\nTrades recording example: \n");
		for (Trade trade : trades) {
			System.out.println(trade.toString());
		}

		System.out.println("\n\nLatest 15 minutes trades recorded: \n");
		List<Trade> latestTrades = TradeServiceUtil.getLatestTrades(15);
		for (Trade trade : latestTrades) {
			System.out.println(trade.toString());
		}

		System.out.println("\n\nStock price for latest 15 minutes trades: "
				+ TradeServiceUtil.getStockPrice(latestTrades) + "\n");

		String tickerPriceStr = "1.5";
		BigDecimal tickerPrice = new BigDecimal(tickerPriceStr);

		System.out.println("\n\nDividend Yield calculation (ticker price = " + tickerPriceStr + "): ");
		for (Stock stock : stocks) {

			String divYield = "N/A";
			try {
				divYield = StockServiceUtil.getDividendYield(stock, tickerPrice).toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(stock.toString() + "; Dividend Yield: " + divYield);
		}

		System.out.println("\n\nP/E Ratio calculation (ticker price = " + tickerPriceStr + "): ");
		for (Stock stock : stocks) {

			String ratio = "N/A";
			try {
				ratio = StockServiceUtil.getDividendYield(stock, tickerPrice).toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(stock.toString() + "; P/E Ratio: " + ratio);
		}

		BigDecimal[] prices = new BigDecimal[] { new BigDecimal("3.000"), new BigDecimal("3.000"),
				new BigDecimal("3.000") };
		String gbceAllShareIndex = "N/A";
		try {
			gbceAllShareIndex = StockServiceUtil.gbceShareIndex(prices).setScale(3, BigDecimal.ROUND_CEILING)
					.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\n\nP/E GBCE All Share Index calculation: " + gbceAllShareIndex);
	}
}
