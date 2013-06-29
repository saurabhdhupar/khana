package common;

public class AddressNormalizer {
	
	public static String normalizeAddress(String address) {
		return replaceSpecialChar(address);
	}
	
	public static String replaceSpecialChar(String address) {
		return address.replaceAll("[!$%^&*.<>/:;-?'\"`~{}|_]", "");
	}
	
	public static String toLowerCase(String address) {
		return address.toLowerCase();
	}
	
	public static String normalizeDirection(String address) {
		String tokens[] = address.split(" ");
		address = "";
		for (String token : tokens) {
			for (DirectionEnum enum_value : DirectionEnum.values()) {
				if(token.equals(enum_value.getValue())) {
					token = enum_value.getReplacement();
				}
			}
			address += token+" ";
		}
		return address.trim();
	}
	
	public static String normalizeStreetNameForNumbers(String address) {
		String tokens[] = address.split(" ");
		address = "";
		for (String token : tokens) {
			String normalized = OrdinalValuesNormalization.getNormalizeOrdinalValues(token);
			if(normalized != null) {
				token = normalized; 
			}
			address += token+" ";
		}
		return address.trim();
	}
	
	public static String normalizeStreetNames(String address) {
		String tokens[] = address.split(" ");
		address = "";
		for (String token : tokens) {
			String normalizeStreet = StreeNameNormalizerMap.checkValue(token);
			if(normalizeStreet != null) {
				address += normalizeStreet+" ";
			}
			else{
				address += token+" ";
			}
		}
		return address;
	}
	
	public static String getNormalizeStreetAddress(String address) {
		return normalizeStreetNameForNumbers(normalizeStreetNames(normalizeDirection(toLowerCase(normalizeAddress(address)))));
	}
	
	public static void main(String args[]) {
		System.out.println(normalizeStreetNameForNumbers(normalizeStreetNames(normalizeDirection(toLowerCase(normalizeAddress("100 W American Canyon Rd"))))));
	}
}
