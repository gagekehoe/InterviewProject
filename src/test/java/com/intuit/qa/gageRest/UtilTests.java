package com.intuit.qa.gageRest;


import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.intuit.qa.gageRest.controllers.Util;

public class UtilTests {

	@Test
	public void makeHttpCallTest() {
		Util util = new Util();
		String response = util.makeHttpCall("http://jsonplaceholder.typicode.com/users");
		Assert.assertTrue(response.length() > 0);
	}
	
	@Test
	public void parseJsonTest() {
		Util util = new Util();
		String response = util.makeHttpCall("http://jsonplaceholder.typicode.com/users");
		Assert.assertTrue(response.length() > 0);
		JSONArray jsonArray = util.parseJSON(response);
		Assert.assertTrue(jsonArray != null);
	}
	
	@Test
	public void sanitizePhoneTest() {
		Util util = new Util();
		String response = util.makeHttpCall("http://jsonplaceholder.typicode.com/users");
		Assert.assertTrue(response.length() > 0);
		JSONArray jsonArray = util.parseJSON(response);
		Assert.assertTrue(jsonArray != null);
		jsonArray = util.sanitizeData(jsonArray);
		try {
			System.out.println(jsonArray.toString(2));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
