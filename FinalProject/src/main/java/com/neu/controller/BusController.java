package com.neu.controller;

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

import com.neu.dao.BusDao;
import com.neu.pojo.Bus;

@Controller
@RequestMapping(value = "/*Bus.htm")
public class BusController {

	@Autowired
	@Qualifier("busValidator")
	BusValidator busValidator;
	
	@Autowired
	@Qualifier("adao")
	BusDao adao;
	
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(busValidator);
	} 
	
	@RequestMapping(value = "/addBus.htm", method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("bus") Bus bus,BindingResult result) throws Exception
	{
		busValidator.validate(bus,result);
		if(result.hasErrors()){
    		return "addBus"; 
    	}
		
		try{
			
			String name= bus.getBusName();
			name = name.replaceAll("[^A-Za-z]+$", "");
			String owner = bus.getOwner();
			owner = owner.replaceAll("[^A-Za-z]+$", "");
			
			adao.create(name, owner);
			
		}
		catch(Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }

		return "addedBus";	
	}

	
	
	
	@RequestMapping(value = "/addBus.htm", method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("bus") Bus bus,BindingResult result)
	{
		
		return "addBus";
	}
	
	@RequestMapping(value = "/deleteBus.htm", method = RequestMethod.GET)
	public String delete()
	{
		
		return "deleteBus";
	}
	
	@RequestMapping(value = "/deleteBus.htm", method = RequestMethod.POST)
	public String deleteBus(HttpServletRequest request)
	{
		
int a=0;
		
		try{
			
			String id = request.getParameter("bus_id");
			id = id.replaceAll("[^\\d]+$", "");
			
		    long bus_id = Long.parseLong(id);		
		
		     a= adao.deleteBus(bus_id);
		     
		}
		
		catch(Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
            
        }
        
		HttpSession session = request.getSession();
		if(a==0)
		{
			request.setAttribute("delete", -1);
			return "deleteBus";
		}
    
		request.setAttribute("delete", 1);
	return "deletedBus";
		
		
	
	}
	

	
}