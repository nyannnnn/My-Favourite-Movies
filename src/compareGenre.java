//******************************************************************
//Name: Max Luo
//Date: 4/1/2022
//Description: This class uses the comparator interface to compare the movie genres
//******************************************************************
import java.util.*;

public class compareGenre implements Comparator <Movies>{

	// Description: compares two movie genres
	// parameters: object movie m1 and object movie m2
	// return: integer value of the difference between m1 and m2
	public int compare(Movies m1, Movies m2) {
		return m1.getGenre().compareToIgnoreCase(m2.getGenre());
	}
	
}
