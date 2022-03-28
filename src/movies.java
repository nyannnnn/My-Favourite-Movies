
public class movies {

	private String movie;
	private String rating;
	private String genre;

	private static int numMovies;
	
	//getters and setters
	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public static int getNumMovies(int numMovies) {
		return numMovies;
	}
	
	//constructor
	public movies(String rating, String movie, String genre) {
		this.movie = movie;
		this.rating = rating;
		this.genre = genre;
		numMovies++;
	}
	
	

}
