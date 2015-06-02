package org.alch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class Utils {

	public static JSONObject getItemPrice(String item) {
		String line = getContents("http://api.rsbuddy.com/grandExchange?a=guidePrice&i="+item);
		JSONObject obj = new JSONObject(line);
		return obj;
	}
	
	public static JSONObject  getDatabase() {
	    String line = getContents("https://rsbuddy.com/exchange/names.json");
	    JSONObject obj = new JSONObject(line);
	    return obj;
	}
	
	public static JSONObject getItemPrices() {
		String line = getContents("https://rsbuddy.com/exchange/summary.json");
		return new JSONObject(line);
	}
	
	private static String getContents(String webPage) {
		URL url;
	    InputStream is = null;
	    BufferedReader br;
	    String line = null;
	    try {
	        url = new URL(webPage);
	        is = url.openStream();  // throws an IOException
	        br = new BufferedReader(new InputStreamReader(is));
	        line = br.readLine();
	        
	    } catch (MalformedURLException mue) {
	         mue.printStackTrace();
	    } catch (IOException ioe) {
	         ioe.printStackTrace();
	    } finally {
	        try {
	            if (is != null) is.close();
	        } catch (IOException ioe) {
	            // nothing to see here
	        }
	    }
	    return line;
	}
}
