
public class Movies {

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
	public Movies(String rating, String movie, String genre) {
		this.movie = movie;
		this.rating = rating;
		this.genre = genre;
		numMovies++;
	}
	
	public String toString() {
		return String.format("Movie title: %s%n"
						   + "Genre: %s%n"
						   + "Rating: %s%n"
						   + "Ranking: ", this.movie, this.genre, this.rating);
	}

}
