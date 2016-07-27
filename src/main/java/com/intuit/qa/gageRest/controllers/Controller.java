package com.intuit.qa.gageRest.controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
	
	@RequestMapping("/test")
    public String test() {
        return "this is a test";
    }
	
	@RequestMapping(value = "/getSanitized", produces = "application/json; charset=utf-8")
	public String getSanitized() {
		Util util = new Util();
		String response = util.makeHttpCall("http://jsonplaceholder.typicode.com/users");
		JSONArray jsonArray = util.parseJSON(response);
		jsonArray = util.sanitizeData(jsonArray);
		try {
			return jsonArray.toString(2);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "Failed";
	}

}
