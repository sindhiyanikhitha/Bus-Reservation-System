package com.neu.controller;

import java.io.ByteArrayOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.neu.dao.PassengerDao;
import com.neu.dao.TicketDao;
import com.neu.pojo.BusDetails;
import com.neu.pojo.Passenger;


@Controller
@RequestMapping(value="/*Ticket.*")
public class TicketController {

	@Autowired
	@Qualifier("pdao")
	PassengerDao pdao;
	
	@Autowired
	@Qualifier("tdao")
	TicketDao tdao;
	
	@RequestMapping(value="/downloadTicket.pdf", method=RequestMethod.GET)
	public void downloadTicket(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		long passenger_id = ((Long) session.getAttribute("passenger_id"));
		Passenger passenger = pdao.searchPassenger(passenger_id);
		BusDetails busDetail = (BusDetails)session.getAttribute("busdetail");
		
		
		try{
		
		response.setContentType("application/pdf");
		
		Document document = new Document();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();
        Paragraph title = new Paragraph("Ticket Confirmation");
        Paragraph name = new Paragraph("Passenger name:"+passenger.getFirstName()+""+passenger.getLastName());
        Paragraph BusDetail = new Paragraph("Bus Detail Name"+busDetail.getBus_name()+" From "+busDetail.getFrom()+" Destination "+busDetail.getDest());
        Paragraph deptDetails = new Paragraph("Departure Date"+busDetail.getDeptDate()+"Departure Time :"+busDetail.getDeptTime());
        Paragraph arrDetails = new Paragraph("Destination Arrival Date"+busDetail.getArrDate()+"Destination Arrival Time"+busDetail.getArrivalTime());
        
        document.add(title);
        document.add(name);
        document.add(BusDetail);
        document.add(deptDetails);
        document.add(arrDetails);
        
        document.close();
        
        ServletOutputStream out = response.getOutputStream();
        baos.writeTo(out);
        out.flush();
        
        
		}
		
		catch(Exception e)
		{
			System.out.println("Could not add ticket object"+e.getMessage());
		}
		
	}
	
	@RequestMapping(value="/emailTicket.htm", method=RequestMethod.GET)
	public String emailTicket(HttpServletRequest request,PassengerDao pdao) throws Exception
	{
		HttpSession session = request.getSession();
		long passenger_id = ((Long) session.getAttribute("passenger_id"));
		Passenger pass = pdao.searchPassenger(passenger_id);
		BusDetails bus = (BusDetails) session.getAttribute("bus");

		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("nikhitha.nikki888@gmail.com", "sssss"));
		email.setSSLOnConnect(true);
		try {
			email.setFrom("nikhitha.nikki888@gmail.com");
			email.setSubject("Ticket Confirmation");
			email.setMsg("Hello,Customer:" + pass.getFirstName() + " " + pass.getLastName() + "\n"
					+ "Thank you for booking Ticket with us. Please find your movie details below " + "\n"
					+ "Bus Name" + bus.getBus_name() +  "\n"
					+ "Depature Date" + bus.getDeptDate() + "Depature  Time :" + bus.getDeptTime() + "\n");
			email.addTo(pass.getEmail());
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	 catch (Exception e) {
		System.out.println("Could not send email " + e.getMessage());
	}

	return "confirmation";
	}
	
	@RequestMapping(value="/deleteTicket.htm", method=RequestMethod.GET)
	public String deleteTicket(HttpServletRequest request) throws Exception
	{
		try{
		HttpSession session = request.getSession();
		long passenger_id = ((Long) session.getAttribute("passenger_id"));
		Passenger passenger = pdao.searchPassenger(passenger_id);
		BusDetails busDetail = (BusDetails)session.getAttribute("busdetail");
		tdao.cancelTicket(passenger, busDetail);
		
		
		}
		catch(Exception e)
		{
			System.out.println("Could not cancel Ticket "+e.getMessage());
		}
		
		return "confirmation";
	}
}
