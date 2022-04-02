//******************************************************************
//Name: Max Luo
//Date: 4/1/2022
//Description: This program reads a text file named movies.txt and searches for either the movie title or a list of movie genre and outputs it
//******************************************************************
import java.io.*;
import java.util.*;

public class MoviesController {

	public static void main(String args[]) {
		ArrayList<Movies> movieList = new ArrayList<>();
		// reading in the file
		try {
			BufferedReader in = new BufferedReader(new FileReader("movies.txt"));
			String line = "";
			while ((line = in.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line.trim());
				if (st.countTokens() < 3) {
					continue;
				}
				String rating = st.nextToken();
				double rate;
				// if rate is not a double, then move onto next line
				try {
					rate = Double.parseDouble(rating.substring(0, rating.length() - 1));
					if (!rating.substring(rating.length() - 1).equals("%")) {
						throw new NumberFormatException();
					}
				} catch (NumberFormatException e) {
					continue;
				}
				String name = "";
				// reading in all tokens except for the last one for title
				int size = st.countTokens() - 1;	
				for (int i = 0; i < size; i++) {
					name += st.nextToken() + " ";
				}
				name = name.trim();
				// reading in the last token as genre
				String genre = st.nextToken();
				movieList.add(new Movies(rate, name, genre, false));
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("IOException!!!!!");
		}

		// sorting the array by rating first so it doesnt need to be sorted again
		// main loop
		Scanner sc = new Scanner(System.in);
		Collections.sort(movieList);
		movieList.get(0).setRank(1);
		for (int i = 1; i < movieList.size(); i++) {
			if (movieList.get(i).getRating() == movieList.get(i - 1).getRating()) {
				movieList.get(i).setRank(movieList.get(i - 1).getRank());
			} else {
				movieList.get(i).setRank(i + 1);
			}
		}
		ArrayList<Movies> temp = new ArrayList<>();
		while (true) {
			temp.clear();
			System.out.println("Do you want to search by title or genre? (enter exit to exit the program)");
			String choice = sc.nextLine();
			// try and catch for invalid inputs
			try {
				if (choice.equals("title")) {
					// sort movie by title
					Collections.sort(movieList, new compareTitle());
					System.out.println("What movie title do you want to search by");
					String title = sc.nextLine();
					// binary searching for the title
					int index = Collections.binarySearch(movieList, new Movies(0.0, title, null, true),
							new compareTitle());
					if (index > -1) {
						System.out.println(movieList.get(index) + "\n");
					} else {
						System.out.println("Title not found!");
					}
				} else if (choice.equals("genre")) {
					System.out.println("What movie genre do you want to search by");
					String genre = sc.nextLine();
					// sorting movielist by genre first, and then using loops to located the range
					// for the genre asked because all of them will be right next to each other
					Collections.sort(movieList, new compareGenre());
					int index = Collections.binarySearch(movieList, new Movies(0.0, null, genre, true),
							new compareGenre());
					if (index >= 0) {
						temp.add(movieList.get(index));
						int left = index - 1;
						int right = index + 1;
						// looping the left boundary
						while (left >= 0) {
							if (movieList.get(left).getGenre().equalsIgnoreCase(genre)) {
								temp.add(movieList.get(left));
								left--;
							} else {
								break;
							}
						}
						// looping the right boundary
						while (right < movieList.size()) {
							if (movieList.get(right).getGenre().equalsIgnoreCase(genre)) {
								temp.add(movieList.get(right));
								right++;
							} else {
								break;
							}
						}
						Collections.sort(movieList, new compareTitle());
						for (Movies m : temp) {
							System.out.println(m + "\n");
						}
					} else {
						System.out.println("Cannot find the genre");
					}
				} else if (choice.equals("exit")) {
					break;
				} else {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input, re-enter");
			}
		}
	}

}