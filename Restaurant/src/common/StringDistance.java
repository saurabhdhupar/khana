package common;

import com.wcohen.ss.JaroWinkler;

public class StringDistance {
	
	private static JaroWinkler jaroWinklerDistanceFinder = new JaroWinkler(); 
	
	public static double findSimillarityByJaroWinkler(String s1, String s2) {
		return jaroWinklerDistanceFinder.score(s1, s2);
	}
}
