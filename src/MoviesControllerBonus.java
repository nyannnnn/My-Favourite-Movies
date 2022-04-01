//******************************************************************
//Name: Max Luo
//Date: 4/1/2022
//Description: This program reads a text file named movies.txt and searches for either the movie title or a list of movie genre and outputs it (bonus version)
//******************************************************************
import java.io.*;
import java.util.*;

public class MoviesControllerBonus {

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

		//sorting the array by rating first so it doesnt need to be sorted again
		Collections.sort(movieList);
		movieList.get(0).setRank(1);
		for (int i = 1; i < movieList.size(); i++) {
			if (movieList.get(i).getRating() == movieList.get(i - 1).getRating()) {
				movieList.get(i).setRank(i);
			} else {
				movieList.get(i).setRank(i + 1);
			}
		}
		ArrayList<Movies> temp = new ArrayList<>();
		// main loop
		Scanner sc = new Scanner(System.in);
		while (true) {
			temp.clear();
			boolean sortG = false;
			boolean sortR = false;
			boolean sortT = false;
			System.out.println("Do you want to search by title or genre? (enter exit to exit the program)");
			String choice = sc.nextLine();
			// try and catch for invalid inputs
			try {
				if (choice.equalsIgnoreCase("title")) {
					// sort movie by title
					Collections.sort(movieList, new compareTitle());
					System.out.println("What movie title do you want to search by");
					String title = sc.nextLine();
					// binary searching for the title
					int index = Collections.binarySearch(movieList, new Movies(0.0, title, null, true),
							new compareTitle());
					if (index >= 0) {
						temp.add(movieList.get(index));
						int left = index - 1;
						int right = index + 1;
						// looping the left boundary
						while (left >= 0) {
							if (movieList.get(left).getMovie().equalsIgnoreCase(title)) {
								temp.add(movieList.get(left));
								left--;
							}
							if (movieList.get(left).getGenre().equals(movieList.get(left + 1).getGenre())) {
								sortR = true;
							} else if (movieList.get(left).getRating() == movieList.get(left + 1).getRating()) {
								sortG = true;
							} else {
								break;
							}
						}
						// looping the right boundary
						while (right < movieList.size()) {
							if (movieList.get(right).getMovie().equalsIgnoreCase(title)) {
								temp.add(movieList.get(right));
								right++;
							}
							if (movieList.get(right).getGenre().equals(movieList.get(right - 1).getGenre())) {
								sortR = true;
							} else if (movieList.get(right).getRating() == movieList.get(right - 1).getRating()) {
								sortG = true;
							} else {
								break;
							}
						}
						// outputting duplicates
						if (right != left) {
							System.out.println(
									"many duplicate results, what do you want to sort the results by? (Genre/Rating)");
							String option = sc.nextLine();
							try {
								// sorting by genre
								if (option.equalsIgnoreCase("genre")) {
									if(sortR) {
										Collections.sort(temp);
									}
									else {
										Collections.sort(temp, new compareGenre());
									}
									//outputting
									for (Movies m : temp) {
										System.out.println(m + "\n");
									}
								}
								// sorting by rating
								else if (option.equalsIgnoreCase("rating")) {
									if(sortG) {
										Collections.sort(temp, new compareGenre());
									}
									else {
										Collections.sort(temp);
									}
									//outputting
									for (Movies m : temp) {
										System.out.println(m + "\n");
									}
								} else {
									throw new NumberFormatException();
								}
							} catch (NumberFormatException e) {
								System.out.println("Invalid input, re-enter");
							}
						} else {
							for (Movies m : temp) {
								System.out.println(m + "\n");
							}
						}
					}
				} else if (choice.equalsIgnoreCase("genre")) {
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
							} if (movieList.get(left).getMovie().equalsIgnoreCase(movieList.get(left + 1).getMovie())) {
								sortR = true;
							} else if (movieList.get(left).getRating() == movieList.get(left + 1).getRating()) {
								sortT = true;
							}else {
								break;
							}
						}
						// looping the right boundary
						while (right < movieList.size()) {
							if (movieList.get(right).getGenre().equalsIgnoreCase(genre)) {
								temp.add(movieList.get(right));
								right++;
							} if (movieList.get(right).getMovie().equalsIgnoreCase(movieList.get(right - 1).getMovie())) {
								sortR = true;
							} else if (movieList.get(right).getRating() == movieList.get(right - 1).getRating()) {
								sortT = true;
							}else {
								break;
							}
						}
						// outputting duplicates
						if (right != left) {
							System.out.println(
									"many duplicate results, what do you want to sort the results by? (Title/Rating)");
							String option = sc.nextLine();
							try {
								// sorting by title
								if (option.equalsIgnoreCase("title")) {
									if(sortR) {
										Collections.sort(temp);
									}
									else {
										Collections.sort(temp, new compareTitle());
									}
									for (Movies m : temp) {
										System.out.println(m + "\n");
									}
								}
								// sorting by rating
								else if (option.equalsIgnoreCase("rating")) {
									if(sortT) {
										Collections.sort(temp, new compareTitle());
									}
									else {
										Collections.sort(temp);
									}
									for (Movies m : temp) {
										System.out.println(m + "\n");
									}
								} else {
									throw new NumberFormatException();
								}
							} catch (NumberFormatException e) {
								System.out.println("Invalid input, re-enter");
							}
						} else {
							for (Movies m : temp) {
								System.out.println(m + "\n");
							}
						}
					} else {
						System.out.println("Cannot find the genre");
					}
				} else if (choice.equalsIgnoreCase("exit")) {
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