package com.camara.organizada.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Util {
	
	public Util() {
		
	}
	
	public boolean isValidString(String str) {
		
		boolean isValid = true;
		
		if(str.equals(null) || str.trim().isEmpty()) {
			isValid = false;
		}
		
		return isValid;
	}
	
	public boolean isValidDNI(String dni) {
		
		boolean isValid = true;
		
		if(!isStringAllDigit(dni)) {
			isValid = false;
		}
		
      return isValid;
	}
	
	private boolean isStringAllDigit(String str) {
		boolean isDigit = true;
		
		char[] chars = str.toCharArray();
		int index = 0;
		while (isDigit && index < chars.length){
			if(!Character.isDigit(chars[index]) && (chars[index] == '-' && index != chars.length - 2)){
				isDigit = false;
	        }
				
			index++;
	         
       }
		if(chars[chars.length - 2]!= '-') {
			isDigit = false;
		}
			
	      return isDigit;
	      
		
	}
	
	
	public boolean isValidInitDate(LocalDate dateParsed) {
		boolean isValid = true;				
			
		LocalDate current_date = LocalDate.now();

		if(dateParsed.isAfter(current_date)) {
			isValid = false;
		}
	
		return isValid;
	}

	public LocalDate parseString2Date(String date) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                 
        LocalDate dateParsed = LocalDate.parse(date, dateTimeFormatter);
        
        return dateParsed;
	}
}
