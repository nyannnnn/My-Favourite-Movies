import java.io.*;
import java.util.*;

public class MoviesController {

	public static void main(String args[]) {
		ArrayList<Movies> movieList = new ArrayList<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader("movies.txt"));
			String line = "";
			while ((line = in.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line.trim());
				if(st.countTokens() != 3) {
					continue;
				}
				String rating = st.nextToken().trim();
				if(rating.indexOf("%") != rating.length()-1) {
					continue;
				}
				double d = Double.parseDouble(rating.substring(0, rating.length()));
				String name = "";
				int size = st.countTokens() - 1;
				for (int i = 0; i < size; i++) {
					name += st.nextToken() + " ";
				}
				name = name.trim();
				String genre = st.nextToken();
				if(name == null || genre == null) {
					continue;
				}
				movieList.add(new Movies(rating, name, genre, false));
			}
		} catch (NumberFormatException e){
			
		}catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("IOException!!!!!");
		}

		ArrayList<Movies> temp = new ArrayList<>();
		// main loop
		Scanner sc = new Scanner(System.in);
		while (true) {
			temp.clear();
			Collections.sort(movieList);
			movieList.get(0).setRank(1);
			for (int i = 1; i < movieList.size(); i++) {
				if (movieList.get(i).getRating().equals(movieList.get(i - 1).getRating())) {
					movieList.get(i).setRank(i);
				} else {
					movieList.get(i).setRank(i + 1);
				}
			}
			System.out.println("Do you want to search by title or genre? (enter exit to exit the program)");
			String choice = sc.nextLine();
			try {
				if (choice.equals("title")) {
					Collections.sort(movieList, new compareTitle());
					System.out.println("What movie title do you want to search by");
					String title = sc.nextLine();
					int index = Collections.binarySearch(movieList, new Movies(null, title, null, true),
							new compareTitle());
					if (index > -1) {
						System.out.println(movieList.get(index) + "\n");
					}
				} else if (choice.equals("genre")) {
					System.out.println("What movie genre do you want to search by");
					String genre = sc.nextLine();
					// you implement while loop to search for a chunk of the same genre and print
					// them out!!!!!
					Collections.sort(movieList, new compareGenre());
					int index = Collections.binarySearch(movieList, new Movies(null, null, genre, true),
							new compareGenre());
					if (index >= 0) {
						temp.add(movieList.get(index));
						int left = index - 1;
						int right = index + 1;
						while (left >= 0) {
							if (movieList.get(left).getGenre().equalsIgnoreCase(genre)) {
								temp.add(movieList.get(left));
								left--;
							} else {
								break;
							}
						}
						while (right < movieList.size()) {
							if (movieList.get(right).getGenre().equalsIgnoreCase(genre)) {
								temp.add(movieList.get(right));
								right++;
							} else {
								break;
							}
						}
						for (Movies m : temp) {
							System.out.println(m + "\n");
						}
					} else {
						System.out.println("CANNOT FIND BOZO");
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
		sc.close();
	}

}