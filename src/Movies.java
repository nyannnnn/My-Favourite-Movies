
public class Movies implements Comparable<Movies> {

	//initalizing variables
	private String movie;
	private double rating;
	private String genre;
	private int rank;

	private static int numMovies;

	// getters and setters
	public String getMovie() {
		return movie;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public static int getNumMovies() {
		return numMovies;
	}

	// constructor
	public Movies(double rating, String movie, String genre, boolean isTemp) {
		if (isTemp) {
			this.movie = movie;
			this.rating = rating;
			this.genre = genre;
		} else {
			this.movie = movie;
			this.rating = rating;
			this.genre = genre;
			numMovies++;
		}
	}

	//natural comparing method for sorting by rating
	public int compareTo(Movies m) {
		if(m.rating == this.rating) {
			return 0;
		}
		else if(m.rating < this.rating) {
			return -1;
		}
		return 1;
	}

	//toString method for System.out.println();
	public String toString() {
		return String.format("Movie title: %s%n" + "Genre: %s%n" + "Rating: %s%%%n" + "Ranking: %d out of %d",
				this.movie, this.genre, this.rating, this.rank, numMovies);
	}

}
