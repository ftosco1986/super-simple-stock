package com.example.supersimplestock.model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Trade {

	private long tradeId;

	private Stock stock;
	private long timestamp;
	private int shareQty;
	private String buyer;
	private String seller;
	private BigDecimal price;

	public Trade(long tradeId, Stock stock, long timestamp, int shareQty, String buyer, String seller,
			BigDecimal price) {
		setTradeId(tradeId);
		setStock(stock);
		setTimestamp(timestamp);
		setShareQty(shareQty);
		setBuyer(buyer);
		setSeller(seller);
		setPrice(price);
	}

	public long getTradeId() {
		return tradeId;
	}

	public void setTradeId(long tradeId) {
		this.tradeId = tradeId;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getShareQty() {
		return shareQty;
	}

	public void setShareQty(int shareQty) {
		this.shareQty = shareQty;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	@Override
	public String toString() {

		DateFormat df = new SimpleDateFormat("MM/dd/YYYY HH:mm");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(getTimestamp());

		return "id: " + getTradeId() + "; Time: " + df.format(calendar.getTime()) + "; Share qty: " + getShareQty()
				+ "; Buyer: " + getBuyer() + "; Seller: " + getSeller() + "; Price: " + getPrice().toString() + "£";
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
