package com.agvinsant.java1week2;

import org.json.JSONException;
import org.json.JSONObject;

import com.agvinsant.lib.Prices;


public class JSON {
	// Builds the JSON and returns the JSON object

public static JSONObject buildJSON(){
	
	// creating the genre object
	JSONObject genreObject = new JSONObject();
	
try {
	
	// creating the query object
			JSONObject queryObject = new JSONObject();
			
			for (Prices prices : Prices.values()){
				
				JSONObject infoObject = new JSONObject();
				
				infoObject.put("computerType", prices.setComputerType());
				infoObject.put("computerValue", prices.setComputerValue());
				queryObject.put(prices.name().toString(), infoObject);
			}
	
} catch (JSONException e) {
	e.printStackTrace();
}	
	
	
	return genreObject;
}

public static String readJSON(String selected){
	
	String result, computerType, computerValue;
	JSONObject object = buildJSON();
	
	try {
		computerType = object.getJSONObject("query").getJSONObject(selected).getString("computerType");
		computerValue = object.getJSONObject("query").getJSONObject(selected).getString("computerValue");
		
		result = "Computer Type: " + computerType + "\r\n" +
					"Starting Price: " + computerValue + "\r\n";
		
	} catch (JSONException e) {
		
		e.printStackTrace();
		result = e.toString();
	}
	
	return result;
    }
}



