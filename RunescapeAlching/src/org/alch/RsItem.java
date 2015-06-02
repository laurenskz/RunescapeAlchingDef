package org.alch;

import org.json.JSONObject;

public class RsItem {
	
	private String name;
	private int price;
	private int profit;
	private int marketPrice;
	
	public RsItem(JSONObject jsonObject) {
		name = jsonObject.getString("name");
		price = (int) Math.round(jsonObject.getInt("store")*0.6);
	}
	
	public void setProfit(int marketPrice) {
		this.marketPrice = marketPrice;
		profit = price - marketPrice - Constants.NATURE_RUNE_PRICE;
		if(price/marketPrice>3)profit = Integer.MIN_VALUE;
	}
	
	public int getProfit() {
		return profit;
	}
	 
	public String getName() {
		return name;
	}
	
	public int getMarketPrice() {
		return marketPrice;
	}
	
	public int getPrice() {
		return price;
	}
}
