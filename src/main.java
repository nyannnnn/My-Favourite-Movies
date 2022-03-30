import java.io.*;
import java.util.*;

public class main {

	public static void main(String args[]) {
		ArrayList<Movies> movieList = new ArrayList<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader("file.txt"));
			String line = "";
			while ((line = in.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line.trim());
				String rating = st.nextToken();
				String name = "";
				int size = st.countTokens() - 1;
				for (int i = 0; i < size; i++) {
					name += st.nextToken() + " ";
				}
				name = name.trim();
				String genre = st.nextToken();
				movieList.add(new Movies(rating, name, genre, false));
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("IOException!!!!!");
		}

		// main loop
		Scanner sc = new Scanner(System.in);
		while (true) {
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
				} else {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input, re-enter");
			}
		}
	}

}