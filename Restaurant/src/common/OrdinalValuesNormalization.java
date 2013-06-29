package common;

import java.util.HashMap;
import java.util.Map;

public class OrdinalValuesNormalization {
	
	private static Map<String,String> ordinalStreetValuesNormalizationMap = new HashMap<String,String>();
	private static String REGEX_MULTIPLE_DIGIT_ORDINAL_STREET_NAME = "[0-9][0-9](st|nd|rd|th)";

	static{
		ordinalStreetValuesNormalizationMap.put("1st","first");
		ordinalStreetValuesNormalizationMap.put("11th","eleventh");
		ordinalStreetValuesNormalizationMap.put("2nd","second");
		ordinalStreetValuesNormalizationMap.put("12th","twelfth");
		ordinalStreetValuesNormalizationMap.put("20th","twentieth");
		ordinalStreetValuesNormalizationMap.put("3rd","third");
		ordinalStreetValuesNormalizationMap.put("13th","thirteenth");
		ordinalStreetValuesNormalizationMap.put("30th","thirtieth");
		ordinalStreetValuesNormalizationMap.put("4th","fourth");
		ordinalStreetValuesNormalizationMap.put("14th","fourteenth");
		ordinalStreetValuesNormalizationMap.put("40th","fortieth");
		ordinalStreetValuesNormalizationMap.put("5th","fifth");
		ordinalStreetValuesNormalizationMap.put("15th","fifteenth");
		ordinalStreetValuesNormalizationMap.put("50th","fiftieth");
		ordinalStreetValuesNormalizationMap.put("6th","sixth");
		ordinalStreetValuesNormalizationMap.put("16th","sixteenth");
		ordinalStreetValuesNormalizationMap.put("60th","sixtieth");
		ordinalStreetValuesNormalizationMap.put("7th","seventh");
		ordinalStreetValuesNormalizationMap.put("17th","seventeenth");
		ordinalStreetValuesNormalizationMap.put("70th","seventieth");
		ordinalStreetValuesNormalizationMap.put("8th","eighth");
		ordinalStreetValuesNormalizationMap.put("18th","eighteenth");
		ordinalStreetValuesNormalizationMap.put("80th","eightieth");
		ordinalStreetValuesNormalizationMap.put("9th","ninth");
		ordinalStreetValuesNormalizationMap.put("19th","nineteenth");
		ordinalStreetValuesNormalizationMap.put("90th","ninetieth");
	}
	
	public static String getNormalizeOrdinalValues(String value) {		
		if(ordinalStreetValuesNormalizationMap.containsKey(value)) {
			return ordinalStreetValuesNormalizationMap.get(value);
		}	
		String match = RegexMatcher.match(value, REGEX_MULTIPLE_DIGIT_ORDINAL_STREET_NAME);
		if(match != null) {
			char[] digits = match.toCharArray();
			String firstNumberStr = NumberWordsMap.getWordValue(digits[0]+"0");
			String secondNumberStr = ordinalStreetValuesNormalizationMap.get(match.substring(1, match.length()));
			return firstNumberStr+"-"+secondNumberStr;
		}
		return null;
	}
	
	public static void main(String args[]) {
		
		System.out.println(OrdinalValuesNormalization.getNormalizeOrdinalValues("16th"));
	}

}
