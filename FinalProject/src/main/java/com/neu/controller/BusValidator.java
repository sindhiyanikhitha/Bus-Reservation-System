package com.neu.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.pojo.Bus;



public class BusValidator implements Validator {

	@Override
	public boolean supports(Class aClass) {
		// TODO Auto-generated method stub
		return aClass.equals(Bus.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		Bus bus =(Bus) obj;
	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"busName","error.invalid.busName","Bus Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"owner","error.invalid.owner","Owner Required");
		
		
	}

}