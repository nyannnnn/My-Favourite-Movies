import java.util.Comparator;

public class compareTitle implements Comparator <Movies>{

	public int compare(Movies m1, Movies m2) {
		return m1.getMovie().compareTo(m2.getMovie());
	}
	
}
