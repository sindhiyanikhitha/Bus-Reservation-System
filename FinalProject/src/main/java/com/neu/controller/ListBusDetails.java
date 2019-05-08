package com.neu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.dao.BusList;
import com.neu.pojo.BusDetails;



@Controller
@RequestMapping(value="/listBusDetails.htm")
public class ListBusDetails {

	@Autowired
	@Qualifier("bldao")
	BusList bldao;
	
	
	@RequestMapping(value = "/listBusDetails.htm", method = RequestMethod.POST)
	public String initializeForm(@ModelAttribute("busDetails") BusDetails busDetails, HttpServletRequest request) throws Exception
	{
		HttpSession session = request.getSession();
		String from = request.getParameter("from");
		from = from.replaceAll("[^A-Za-z]+$", "");
		String dest = request.getParameter("dest");
		dest = dest.replaceAll("[^A-Za-z]+$", "");
		String deptDate = request.getParameter("deptDate");
		String retDate = request.getParameter("arrDate");

		System.out.println("From place"+from+"Dest"+dest+"Dept date"+deptDate);
		
		try{
		
		List<String> buslist = bldao.listBus(from, dest, deptDate);
		
		int length = buslist.size();
		System.out.println("Length is "+length);
		
		
		session.setAttribute("buslist", buslist);
	
		
		}
		catch(Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
		
		return "busDetailList";
	}
	
}
