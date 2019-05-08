package com.neu.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.pojo.BusDetails;


public class BusDetailsValidator implements Validator {

	@Override
	public boolean supports(Class aClass) {
		
		return aClass.equals(BusDetails.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		
		BusDetails bd = (BusDetails)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"bus_name","error.invalid.bus_name","Bus Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"bus_id","error.invalid.bus_id","Route Id Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"from","error.invalid.from","From Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"dest","error.invalid.dest","Dest Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"deptTime","error.invalid.deptTime","Departure Time Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"arrivalTime","error.invalid.arrivalTime","Arrival Time Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"travelClass","error.invalid.travelClass","Travel Class Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"totalSeats","error.invalid.totalSeats","Total Seats Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"availableSeats","error.invalid.availableSeats","Available Seats Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"amount","error.invalid.amount","Amount Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"deptDate","error.invalid.deptDate","Departure Date Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"arrDate","error.invalid.arrDate","Arrival Date Required");
		
	}

	
	
}