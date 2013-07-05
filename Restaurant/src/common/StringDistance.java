package common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.language.Soundex;

import com.wcohen.ss.JaroWinkler;

public class StringDistance {
	
	private static Set<String> stopWords = new HashSet<String>();
	static{
		stopWords.add("cuisine");
		stopWords.add("restaurant");
		stopWords.add("curry");
		stopWords.add("corner");
	}
	private static Soundex soundex = new Soundex();
	private static JaroWinkler jaroWinklerDistanceFinder = new JaroWinkler(); 
	
	public static double findSimillarityByJaroWinkler(String s1, String s2) {
		return jaroWinklerDistanceFinder.score(s1, s2);
	}
	
	public static double findSimiliarityinNames(String s1, String s2) {
		String[] arr_s1 = s1.split(" ");
		removeStopWords(arr_s1);
		String[] arr_s2 = s2.split(" ");
		removeStopWords(arr_s2);
		double count = 0;
		for (String token2 : arr_s2) {
			for (String token1 : arr_s1) {
				String sound1;
				String sound2;
				try {
					sound1 = soundex.soundex(token1);
					sound2 = soundex.soundex(token2);
				} catch (IllegalArgumentException e) {
					count += 1;
					continue;
				}
				if(sound1.equals(sound2)) {
					count += 1;
				}
			}
		}
		return count*2/(arr_s1.length + arr_s2.length); 
	}
	
	private static void removeStopWords(String[] arr_s1) {
		List<String> list_arr = new ArrayList<String>();
		for (String token : arr_s1) {
			if(!stopWords.contains(token.toLowerCase())) {
				list_arr.add(token);
			}
		}
		arr_s1 = new String[list_arr.size()];
		int index = 0;
		for (String ele : list_arr) {
			arr_s1[index] = ele;
			index += 1;
		}
	}

	public static void main(String args[]) {
		System.out.println(StringDistance.findSimillarityByJaroWinkler("Himiliyan Flavour", "Himalayan Flavors"));
	}
}
