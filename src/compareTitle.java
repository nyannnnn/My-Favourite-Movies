//******************************************************************
//Name: Max Luo
//Date: 4/1/2022
//Description: This class uses the comparator interface to compare the movie titles
//******************************************************************
import java.util.Comparator;

public class compareTitle implements Comparator <Movies>{

	// Description: compares two movie titles
	// parameters: object movie m1 and object movie m2
	// return: integer value of the difference between m1 and m2
	public int compare(Movies m1, Movies m2) {
		return m1.getMovie().compareToIgnoreCase(m2.getMovie());
	}
	
}
