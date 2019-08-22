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
		
	      for(char c : chars){
	          if(!Character.isDigit(c)){
	        	  isDigit = false;
	          }
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
