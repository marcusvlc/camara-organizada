package com.camara.organizada.utils;

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
			if(!Character.isDigit(chars[index]) && (chars[index] != '-' && index == chars.length - 2)){
				isDigit = false;
	        }
			
			
			
			index++;
	         
       }
		if(chars[chars.length - 2]!= '-') {
			isDigit = false;
		}
			
	      return isDigit;
	      
		
	}
	
	public boolean isValidDate(Date date) {
		boolean isValid = true;				
			
		Date current_date = new Date();

		if(date.after(current_date)) {
			isValid = false;
		}
	
		return isValid;
	}

}
