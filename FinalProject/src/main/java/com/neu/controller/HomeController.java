package com.neu.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.pojo.Cities;
import com.neu.dao.BusDetailsDao;
import com.neu.pojo.BusDetails;



@Controller
@RequestMapping(value="/")
public class HomeController {
	
	@Autowired
	@Qualifier("bdao")
	BusDetailsDao bdao;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@ModelAttribute("busDetails")BusDetails busDetails) {

		busDetails.setTravelClass("Economy");
		return "index";
	}
	
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String homePage(@ModelAttribute("busDetails")BusDetails busDetails) {

		busDetails.setTravelClass("AC");
		return "index";
	}
	
	@RequestMapping(value = "/adminHome.htm", method = RequestMethod.GET)
	public String adminHomePage() {

		
		return "adminHome";
	}
	
	@RequestMapping(value="/fromCitieslist.htm", method=RequestMethod.POST)
	public void ajaxForCities(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		try{
		
		String fromCities = request.getParameter("fromCities");
		PrintWriter out = response.getWriter();
		
		List list = bdao.listCities(fromCities);
		
		
		  List<Cities> cities= (ArrayList<Cities>)list;
		  
          JSONArray jsonArray = new JSONArray();
          for (int i=0; i < cities.size(); i++) {
              JSONObject obj = new JSONObject();
              obj.put("cityname", cities.get(i).getCityname());
              
              jsonArray.put(obj);
          }
  
          JSONObject Obj = new JSONObject();
          Obj.put("list", jsonArray);
          out.println(Obj);
		
		}
		catch(Exception e)
		{
			System.out.println("Exception in listing cities"+e.getMessage());
		}
	}
}
	

