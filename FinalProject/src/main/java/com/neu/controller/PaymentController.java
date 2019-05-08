package com.neu.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.neu.dao.PassengerDao;
import com.neu.dao.TicketDao;
import com.neu.pojo.BusDetails;
import com.neu.pojo.Passenger;
import com.neu.pojo.Payment;

@Controller
@RequestMapping(value = "/payment*.htm")
public class PaymentController {

	@Autowired
	@Qualifier("pdao")
	PassengerDao pdao;

	@Autowired
	@Qualifier("paymentValidator")
	PaymentValidator paymentValidator;

	@Autowired
	@Qualifier("tdao")
	TicketDao tdao;

	@Autowired
	@Qualifier("bdao")
	BusDetailsDao bdao;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(paymentValidator);
	}

	
	@RequestMapping(value = "/payment.htm", method = RequestMethod.GET)
	public String initialize(@ModelAttribute("payment") Payment payment) {
		
		
		return "payment";
	}

	@RequestMapping(value = "/payment.htm", method = RequestMethod.POST)
	public String addPayment(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		long passenger_id = ((Long) session.getAttribute("passenger_id"));

		try {

			String number = request.getParameter("creditCardNumber");
			number = number.replaceAll("[^0-9]", "");
			long creditCardNumber = Long.parseLong(number);
			
			String bankName = request.getParameter("bankName");
			bankName = bankName.replaceAll("[^\\dA-Za-z]", "");
			String fullName = request.getParameter("fullName");
			fullName = fullName.replaceAll("[^A-Za-z]+$", "");
			String expiration_month = request.getParameter("expiration_month");
			expiration_month = expiration_month.replaceAll("[^0-9]", "");
			Cookie exp_month = new Cookie("month", expiration_month);
			String expiration_year = request.getParameter("expiration_year");
			expiration_year = expiration_year.replaceAll("[^0-9]", "");
			Cookie exp_year = new Cookie("year", expiration_year);
			Payment paymnt = pdao.createPayment(creditCardNumber, bankName, fullName, expiration_month,
					expiration_year);

			pdao.updatePassenger(passenger_id, paymnt);

			Passenger passenger = pdao.searchPassenger(passenger_id);
			BusDetails busDetail = (BusDetails) session.getAttribute("busdetail");

			tdao.bookTicket(passenger, busDetail);
			int availSeats = busDetail.getAvailableSeats();
			bdao.updateAvailableSeats(busDetail, availSeats, availSeats-1);
			
			
			response.addCookie(exp_month);
			response.addCookie(exp_year);

		} catch (Exception e) {
			System.out.println("Could not add payment/ticket" + e.getMessage());
		}

		return "printTicket";
	}
}