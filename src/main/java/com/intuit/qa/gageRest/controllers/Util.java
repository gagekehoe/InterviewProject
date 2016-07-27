package com.intuit.qa.gageRest.controllers;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

public class Util {
	
	Logger log = Logger.getAnonymousLogger();
	
	public Util() {
	}
	
	public String makeHttpCall(String url) {
		String response = "";
		HttpURLConnection connection = null;
		try {
			URL urlConn = new URL(url);
			connection = (HttpURLConnection) urlConn.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			InputStream in = connection.getInputStream();
			int read = 0;
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			
			while((read = in.read(buffer)) > 0) {
				bout.write(buffer, 0, read);
			}
			bout.flush();
			bout.close();
			in.close();
			
			response = new String(bout.toByteArray());
			
		} catch (Exception e) {
			log.severe("An error has occured Reason: " + e.getMessage());
		}
		return response;
	}
	
	public JSONArray parseJSON(String json) {
		try {
			return new JSONArray(json);
		} catch (Exception e) {
			log.severe("An error has occured. Reason: " + e.getMessage());
		}
		return null;
	}
	
	//1-770-736-8031 x56442
	public JSONArray sanitizeData(JSONArray jsonArray) {
		try {
			for(int i = 0; i < jsonArray.length(); i++) {
				JSONObject user = jsonArray.getJSONObject(i);
				String phoneNum = (String) user.get("phone");
				char[] newPhoneNum = new char[phoneNum.length()];
				for(int j = 0; j < phoneNum.length(); j++) {
					if(Character.isDigit(phoneNum.charAt(j))) {
						newPhoneNum[j] = '1';
					} else {
						newPhoneNum[j] = phoneNum.charAt(j);
					}
				}
				user.remove("phone");
				user.put("phone", new String(newPhoneNum));
			}
			return jsonArray;
		} catch (Exception e) {
			log.severe("An error has occured Reason: " + e.getMessage());
		}
		return jsonArray;
	}

}
