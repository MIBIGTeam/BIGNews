package com.hackaton.util;

public class StringModifier {		
	public static String modifyString(String input){		
		return input.replaceAll("\\s+","").replace('#', ',');
	}
}
