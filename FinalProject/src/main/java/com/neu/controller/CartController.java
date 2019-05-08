package com.neu.controller;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.dao.BusDetailsDao;
import com.neu.pojo.BusDetails;


@Controller
@RequestMapping(value="/*Cart.htm")
public class CartController {

	@Autowired
	@Qualifier("bdao")
	BusDetailsDao bdao;
	
	@RequestMapping(value="/addToCart.htm", method=RequestMethod.GET)
	public String intialize(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{
		
		try{
		HttpSession session = request.getSession();
		System.out.println("route id" + request.getParameter("fid"));
		Long routeid = Long.parseLong(request.getParameter("fid"));
		System.out.println("Route ID is"+routeid);
		List<BusDetails> cart;
 
		BusDetails bd = bdao.searchBusByID(routeid);
		
		System.out.println("Cart Controller:"+bd.getAvailableSeats());
		
		int noOfSeats = bd.getAvailableSeats();
		PrintWriter out = response.getWriter();
		if(noOfSeats>0)
		{
			
			out.println("Seats are available");
			if (session.getAttribute("cart") != null) {
	             cart = (ArrayList<BusDetails>) session.getAttribute("cart");
	         } else {
	             cart = new ArrayList<BusDetails>();
	         }
			
			 cart.add(bd);
			 session.setAttribute("cart", cart);
			 session.setAttribute("busdetail", bd);
			 
			 float total = 0;
	         for (BusDetails f : cart) {
	             total = total + f.getAmount();
	         }
	         
	         session.setAttribute("total", total);
	         
	         return "viewCart";
	         
			}
		
		else
		{
			
			return "notAvailable";
			
		}	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}

	}

	@RequestMapping(value="/removeFromCart.htm", method=RequestMethod.GET)
	public String removeItems(HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		try{
			
			List<BusDetails> cart =(ArrayList<BusDetails>) session.getAttribute("cart");
			String id = request.getParameter("id");
			long route_id = Long.parseLong(id); 
			
			
			for(BusDetails fd:cart){
				if(fd.getRoute_id()==route_id){
					cart.remove(fd);
					break;
					
				}
			}
			
			session.setAttribute("cart", cart);
			
			 float total = 0;
	         for (BusDetails f : cart) {
	             total = total + f.getAmount();
	         }
	         
	         session.setAttribute("total", total);
		}
		
		catch(Exception e)
		{
			 System.out.println("Exception: " + e.getMessage());
		}
		
		
		return "viewCart";
	}
	
	@RequestMapping(value="/viewCart.htm", method=RequestMethod.GET)
	public String viewCart(HttpServletRequest request) {
		return "viewCart";
		
	}
		
	}