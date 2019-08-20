package com.camara.organizada.utils;

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
		char[] chars = dni.toCharArray();
		
	      for(char c : chars){
	          if(!Character.isDigit(c)){
	             isValid = false;
	          }
	       }
	      
	      return isValid;
	}

}
