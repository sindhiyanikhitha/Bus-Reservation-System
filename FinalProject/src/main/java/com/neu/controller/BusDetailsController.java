package com.neu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.dao.BusDetailsDao;
import com.neu.controller.BusDetailsValidator;
import com.neu.dao.BusList;
import com.neu.dao.TicketDao;
import com.neu.pojo.BusDetails;



@Controller
@RequestMapping(value = "/*light*.htm")
public class BusDetailsController {

	@Autowired
	@Qualifier("validator")
	BusDetailsValidator validator;
	
	@Autowired
	@Qualifier("bldao")
	BusList bldao;
	
	@Autowired
	@Qualifier("bdao")
	BusDetailsDao bdao;
	
	@Autowired
	@Qualifier("tdao")
	TicketDao tdao;

	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
		
	} 
	
	@RequestMapping(value = "/addBusDetails.htm", method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("bd") BusDetails bd,BindingResult result) throws Exception
	{
		
		validator.validate(bd, result);
    	if(result.hasErrors()){
    		return "addBusDetails"; 
    	}
    	
    	try
        {
    		long bus_id = bd.getBus_id(); 
    		String from=bd.getFrom(); 
    		from = from.replaceAll("[^A-Za-z]+$", "");
    		String dest=bd.getDest(); 
    		dest = dest.replaceAll("[^A-Za-z]+$", dest);
    		String deptTime=bd.getDeptTime();
    		deptTime = deptTime.replaceAll("[^\\d:]", "");
    		String arrivalTime=bd.getArrivalTime(); 
    		arrivalTime = arrivalTime.replaceAll("[^\\d:]", "");
    		String travelClass=bd.getTravelClass();
    		travelClass = travelClass.replaceAll("[^A-Za-z]+$", "");
    		int totalSeats=bd.getTotalSeats(); 
    		int avlseats=bd.getAvailableSeats();
    		int amount=bd.getAmount();
    		String deptDate=bd.getDeptDate(); 
    		String arrDate=bd.getArrDate(); 
    		String bus_name=bd.getBus_name();
    		
    		
            BusDetailsDao bdao = new BusDetailsDao();
            BusDetails bus = bdao.createBusDetails(amount, arrDate, arrivalTime, avlseats, bus_id, bus_name, deptDate, deptTime, dest, from, totalSeats, travelClass);
            
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
        
    	
		return "addedBus";
	}
	

	@RequestMapping(value = "/addBusDetails.htm", method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("bd") BusDetails bd)
	{
		return "addBusDetails";
	}
	
	@RequestMapping(value="/listBusDetails.htm", method=RequestMethod.GET)
	public String ListBusDetails(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		List<String> listOfBusDetails = null;
		
		try{
			
			listOfBusDetails = bldao.listAllBusDetails();
		}
		catch(Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
		
		session.setAttribute("listOfBusDetails",listOfBusDetails);
		return "ListBusDetails";
	}
	
	
	@RequestMapping(value="/updateBusDetails.htm", method=RequestMethod.GET)
	public String updateBusDetails(@ModelAttribute("bd") BusDetails bd,BindingResult result, HttpServletRequest request) throws Exception
	{
		String id = request.getParameter("id");
		long route_id = Long.parseLong(id);
		
		String action = request.getParameter("action");
		
		HttpSession session = request.getSession();
		
		if(action!=null){
		if(action.equalsIgnoreCase("update"))
		{
			BusDetails bus = bdao.searchBusByID(route_id);
			
		     request.setAttribute("bus", bus);
		   
		   return "updateBusDetails";
		   
		}
		
		}
		
		
		
			return "ListBusDetails";
		
		
	}
	
	@RequestMapping(value="/deleteBusDetails.htm", method=RequestMethod.GET)
	public String deleteBusDetails(@ModelAttribute("bd") BusDetails bd, HttpServletRequest request) throws Exception
	{
		try{
			String id = request.getParameter("id");
			
			long route_id = Long.parseLong(id);
			
			HttpSession session = request.getSession();
			
			BusDetails bus = bdao.searchBusByID(route_id);
			
			tdao.deleteTickets(route_id);
			
			bdao.deleteBusDetails(bus);
			
		}
		catch(Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
		HttpSession session = request.getSession();
		List<String> listOfBusDetails = null;
		
		try{
			
			listOfBusDetails = bldao.listAllBusDetails();
		}
		catch(Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
		
		session.setAttribute("listOfBusDetails",listOfBusDetails);
		
		
		return "ListBusDetails";
	}
	
	@RequestMapping(value="/updateBusDetails.htm", method=RequestMethod.POST)
	public String update(@ModelAttribute("bd") BusDetails bd,BindingResult result, HttpServletRequest request)
	{
		//HttpSession session = request.getSession();
		
		try{
			
			bdao.updateBusDetails(bd);
			System.out.println("Now Seats available are"+bd.getAvailableSeats());
			System.out.println("Bus detail saved/updated successfully");
			
		}
		catch(Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
		HttpSession session = request.getSession();
		List<String> listOfBusDetails = null;
		
try{
			
			listOfBusDetails = bldao.listAllBusDetails();
		}
catch(Exception e)
{
    System.out.println("Exception: " + e.getMessage());
}

session.setAttribute("listOfBusDetails",listOfBusDetails);
		
		
		return "ListBusDetails";
	}
}