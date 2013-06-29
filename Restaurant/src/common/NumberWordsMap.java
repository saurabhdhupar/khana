package common;

import java.util.HashMap;
import java.util.Map;

public class NumberWordsMap {

	private static Map<String,String> numberWordsMap = new HashMap<String,String>();

	
	static {
		numberWordsMap.put("0","zero");
		numberWordsMap.put("1","one");
		numberWordsMap.put("2","two");
		numberWordsMap.put("3","three");
		numberWordsMap.put("4","four");
		numberWordsMap.put("5","five");
		numberWordsMap.put("6","six");
		numberWordsMap.put("7","seven");
		numberWordsMap.put("8","eight");
		numberWordsMap.put("9","nine");
		numberWordsMap.put("10","ten");
		numberWordsMap.put("11","eleven");
		numberWordsMap.put("12","twelve");
		numberWordsMap.put("13","thirteen");
		numberWordsMap.put("14","fourteen");
		numberWordsMap.put("15","fifteen");
		numberWordsMap.put("16","sixteen");
		numberWordsMap.put("17","seventeen");
		numberWordsMap.put("18","eighteen");
		numberWordsMap.put("19","nineteen");
		numberWordsMap.put("20","twenty");
		numberWordsMap.put("21","twenty-one");
		numberWordsMap.put("22","twenty-two");
		numberWordsMap.put("23","twenty-three");
		numberWordsMap.put("24","twenty-four");
		numberWordsMap.put("25","twenty-five");
		numberWordsMap.put("26","twenty-six");
		numberWordsMap.put("27","twenty-seven");
		numberWordsMap.put("28","twenty-eight");
		numberWordsMap.put("29","twenty-nine");
		numberWordsMap.put("30","thirty");
		numberWordsMap.put("31","thirty-one");
		numberWordsMap.put("32","thirty-two");
		numberWordsMap.put("33","thirty-three");
		numberWordsMap.put("34","thirty-four");
		numberWordsMap.put("35","thirty-five");
		numberWordsMap.put("36","thirty-six");
		numberWordsMap.put("37","thirty-seven");
		numberWordsMap.put("38","thirty-eight");
		numberWordsMap.put("39","thirty-nine");
		numberWordsMap.put("40","forty");
		numberWordsMap.put("41","forty-one");
		numberWordsMap.put("42","forty-two");
		numberWordsMap.put("43","forty-three");
		numberWordsMap.put("44","forty-four");
		numberWordsMap.put("45","forty-five");
		numberWordsMap.put("46","forty-six");
		numberWordsMap.put("47","forty-seven");
		numberWordsMap.put("48","forty-eight");
		numberWordsMap.put("49","forty-nine");
		numberWordsMap.put("50","fifty");
		numberWordsMap.put("51","fifty-one");
		numberWordsMap.put("52","fifty-two");
		numberWordsMap.put("53","fifty-three");
		numberWordsMap.put("54","fifty-four");
		numberWordsMap.put("55","fifty-five");
		numberWordsMap.put("56","fifty-six");
		numberWordsMap.put("57","fifty-seven");
		numberWordsMap.put("58","fifty-eight");
		numberWordsMap.put("59","fifty-nine");
		numberWordsMap.put("60","sixty");
		numberWordsMap.put("61","sixty-one");
		numberWordsMap.put("62","sixty-two");
		numberWordsMap.put("63","sixty-three");
		numberWordsMap.put("64","sixty-four");
		numberWordsMap.put("65","sixty-five");
		numberWordsMap.put("66","sixty-six");
		numberWordsMap.put("Number","In Words");
		numberWordsMap.put("67","sixty-seven");
		numberWordsMap.put("68","sixty-eight");
		numberWordsMap.put("69","sixty-nine");
		numberWordsMap.put("70","seventy");
		numberWordsMap.put("71","seventy-one");
		numberWordsMap.put("72","seventy-two");
		numberWordsMap.put("73","seventy-three");
		numberWordsMap.put("74","seventy-four");
		numberWordsMap.put("75","seventy-five");
		numberWordsMap.put("76","seventy-six");
		numberWordsMap.put("77","seventy-seven");
		numberWordsMap.put("78","seventy-eight");
		numberWordsMap.put("79","seventy-nine");
		numberWordsMap.put("80","eighty");
		numberWordsMap.put("81","eighty-one");
		numberWordsMap.put("82","eighty-two");
		numberWordsMap.put("83","eighty-three");
		numberWordsMap.put("84","eighty-four");
		numberWordsMap.put("85","eighty-five");
		numberWordsMap.put("86","eighty-six");
		numberWordsMap.put("87","eighty-seven");
		numberWordsMap.put("88","eighty-eight");
		numberWordsMap.put("89","eighty-nine");
		numberWordsMap.put("90","ninety");
		numberWordsMap.put("91","ninety-one");
		numberWordsMap.put("92","ninety-two");
		numberWordsMap.put("93","ninety-three");
		numberWordsMap.put("94","ninety-four");
		numberWordsMap.put("95","ninety-five");
		numberWordsMap.put("96","ninety-six");
		numberWordsMap.put("97","ninety-seven");
		numberWordsMap.put("98","ninety-eight");
		numberWordsMap.put("99","ninety-nine");
		numberWordsMap.put("100","one hundred");
	}
	
	public static String getWordValue(String key) {
		return numberWordsMap.get(key);
	}

}
