package com.example.supersimplestock.model;

import java.math.BigDecimal;

import com.example.supersimplestock.StockConstants;

public class Stock {

	private long stockId;
	private String stockSymbol;
	private int type;
	private BigDecimal lastDividend;
	private BigDecimal fixedDividendPercentage;
	private BigDecimal parValue;

	public Stock(long stockId, String stockSymbol, int type, double lastDividend, double fixedDividendPercentage,
			double parValue) {

		setStockId(stockId);
		setStockSymbol(stockSymbol);
		setType(type);
		setLastDividend(lastDividend);
		setFixedDividendPercentage(fixedDividendPercentage);
		setParValue(parValue);
	}

	public Stock(long stockId, String stockSymbol, int type, BigDecimal lastDividend,
			BigDecimal fixedDividendPercentage, BigDecimal parValue) {

		setStockId(stockId);
		setStockSymbol(stockSymbol);
		setType(type);
		setLastDividend(lastDividend);
		setFixedDividendPercentage(fixedDividendPercentage);
		setParValue(parValue);
	}

	public long getStockId() {
		return stockId;
	}

	public void setStockId(long stockId) {
		this.stockId = stockId;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public BigDecimal getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(BigDecimal lastDividend) {
		this.lastDividend = lastDividend;
	}

	public BigDecimal getFixedDividendPercentage() {
		return fixedDividendPercentage;
	}

	public void setFixedDividendPercentage(BigDecimal fixedDividendPercentage) {
		this.fixedDividendPercentage = fixedDividendPercentage;
	}

	public BigDecimal getParValue() {
		return parValue;
	}

	public void setParValue(BigDecimal parValue) {
		this.parValue = parValue;
	}

	public void setFixedDividendPercentage(double fixedDividendPercentage) {
		setFixedDividendPercentage(new BigDecimal(fixedDividendPercentage));
	}

	public void setParValue(double parValue) {
		setParValue(new BigDecimal(parValue));
	}

	public void setLastDividend(double lastDividend) {
		setLastDividend(new BigDecimal(lastDividend));
	}

	public void setFixedDividendPercentage(String fixedDividendPercentage) {
		setFixedDividendPercentage(new BigDecimal(fixedDividendPercentage));
	}

	public void setParValue(String parValue) {
		setParValue(new BigDecimal(parValue));
	}

	public void setLastDividend(String lastDividend) {
		setLastDividend(new BigDecimal(lastDividend));
	}

	@Override
	public String toString() {

		return "id: " + getStockId() + "; Symbol: " + getStockSymbol() + "; Type: "
				+ StockConstants.STOCK_TYPES[getType()] + "; Last Dividend: " + getLastDividend().toString()
				+ "; Fixed Dividend: " + getFixedDividendPercentage().setScale(1, BigDecimal.ROUND_CEILING).toString()
				+ "%" + "; Par Value: " + getParValue().toString();
	}
}
