//******************************************************************
//Name: Max Luo
//Date: 4/1/2022
//Description: This class initializes all the static and instance varibles and enables data encapsulation
//******************************************************************
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

	// Description: natural sorting algorithm that compares two rating
	// parameters: object movie m
	// return: 0 if the ratings equal, -1 if it is smaller, and 1 if it is bigger
	public int compareTo(Movies m) {
		if(m.rating == this.rating) {
			return 0;
		}
		else if(m.rating < this.rating) {
			return -1;
		}
		return 1;
	}

	// Description: changed the default toString method
	// parameters: nothing
	// return: the formatted string output
	public String toString() {
		return String.format("Movie title: %s%n" + "Genre: %s%n" + "Rating: %s%%%n" + "Ranking: %d out of %d",
				this.movie, this.genre, this.rating, this.rank, numMovies);
	}

}
