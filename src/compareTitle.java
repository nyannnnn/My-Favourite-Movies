import java.util.Comparator;

public class compareTitle implements Comparator <Movies>{

	//comparator for sorting by movie title
	public int compare(Movies m1, Movies m2) {
		return m1.getMovie().compareToIgnoreCase(m2.getMovie());
	}
	
}
