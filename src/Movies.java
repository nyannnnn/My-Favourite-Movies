
public class Movies implements Comparable <Movies>{

	private String movie;
	private String rating;
	private String genre;
	private int rank; 
	
	private static int numMovies;
	
	//getters and setters
	public String getMovie() {
		return movie;
	}

	public void setRank(int rank) {
		this.rank = rank;
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

	public static int getNumMovies() {
		return numMovies;
	}
	
	//constructor
	public Movies(String rating, String movie, String genre, boolean isTemp) {
		if(isTemp) {
			this.movie = movie;
			this.rating = rating;
			this.genre = genre;
		}
		else {
			this.movie = movie;
			this.rating = rating;
			this.genre = genre;
			numMovies++;
		}
	}
	
	public int compareTo(Movies m) {
		double r1 = Double.parseDouble(this.rating.substring(0, this.rating.length()-1))*100;
		double r2 = Double.parseDouble(m.rating.substring(0, m.rating.length()-1))*100;
		
		return (int) (r2-r1);
	}
	
	public String toString() {
		return String.format("Movie title: %s%n"
						   + "Genre: %s%n"
						   + "Rating: %s%n"
						   + "Ranking: %d out of %d", this.movie, this.genre, this.rating, this.rank, numMovies);
	}

}
