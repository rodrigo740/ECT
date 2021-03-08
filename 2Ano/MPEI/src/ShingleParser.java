

import java.util.Set;
import java.util.TreeSet;

public class ShingleParser {
	
	public static Set<String> splitToShingles(String str, int k) {
		
		Set<String> set = new TreeSet<>();
		for(int i = 0; i + k <= str.length(); i++) {
			set.add(str.substring(i,i+k));
		}
		return set; 
	}

}
