package org.alch;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.json.JSONObject;

import ui.DownloadBox;

public class Main {

	
	public static void main(String[] args) {
		System.out.println();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTextPane txt = new JTextPane();
		Font font = new Font("Arial", 0,14);
		txt.setFont(font);
		txt.setEditable(false);
		txt.setSize(400, 400);
		frame.setSize(500, 500);
		JScrollPane jscroll = new JScrollPane(txt);
		jscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frame.add(jscroll, BorderLayout.CENTER);
		ArrayList<RsItem> rsItems = new ArrayList<RsItem>();
		Constants.NATURE_RUNE_PRICE = Utils.getItemPrice("561").getInt("overall");
		JSONObject items = Utils.getDatabase();
		DownloadBox box = new DownloadBox("Rs item calc", 0, items.keySet().size());
		frame.add(box, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		int count = 0;
		JSONObject prices = Utils.getItemPrices();
		for(String key : items.keySet()) {
			JSONObject item = (JSONObject) items.getJSONObject(key);
			RsItem rsItem = new RsItem(item);
			JSONObject specificItem = Utils.getItemPrice(key);
			box.update(count++);
			if(rsItem.getPrice()<200)continue;
			JSONObject marketPrice = prices.getJSONObject(key);
			int buyPrice = marketPrice.getInt("buy_average");
			int sellPrice = marketPrice.getInt("sell_average");
			int overall = marketPrice.getInt("overall_average");
			buyPrice = max(new int[] {buyPrice, sellPrice, overall, specificItem.getInt("overall"),specificItem.getInt("buying"),specificItem.getInt("selling")});
			if(buyPrice == 0)continue;
			rsItem.setProfit(buyPrice);
			rsItems.add(rsItem);
		}
		rsItems.sort(new Comparator<RsItem>() {

			@Override
			public int compare(RsItem o1, RsItem o2) {
				return o2.getProfit()-o1.getProfit();
			}
		});
		int count2 = 0;
		StringBuilder sb = new StringBuilder();
		for(RsItem item : rsItems) {
			String first = item.getName() + " " + item.getProfit() + " gp";
			sb.append( first);
			sb.append(first.length()<16 ? "\t" : "\t\t");
			String alchString = "alch price: " + item.getPrice();
			sb.append(alchString);
			sb.append(alchString.length()<16 ? "\t" : "\t\t");
			sb.append("Market price: " + item.getMarketPrice()+"\n");
			if(count2++==100)break;
		}
		txt.setText(sb.toString());
		System.out.println(sb);
	}
	
	private static int max(int[] input) {
		if(input==null||input.length==0)return 0;
		int highest = Integer.MIN_VALUE;
		for(int value : input) {
			if(value>highest)highest=value;
		}
		return highest;
	}
}
