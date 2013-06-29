package common;

public enum DirectionEnum {
	
	North("n", "north"),
	Northeast("ne" , "northeast"),
	South("s", "south"),
	Northwest("nw", "northwest"), 
	East("e", "east"),
	Southeast("se", "southeast"), 
	West("w", "west"),
	Southwest("sw", "southwest");
	
	private DirectionEnum(String value, String replacement) {
		this.value = value;
		this.replacement = replacement;
	}
	private String value;
	private String replacement;
	
	public String getValue() {
		return value;
	}
	public String getReplacement() {
		return replacement;
	}

}
