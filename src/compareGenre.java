import java.util.*;

public class compareGenre implements Comparator <Movies>{

	public int compare(Movies m1, Movies m2) {
		return m1.getGenre().compareToIgnoreCase(m2.getGenre());
	}
	
}
