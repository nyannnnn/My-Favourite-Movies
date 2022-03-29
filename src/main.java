import java.io.*;
import java.util.*;

public class main {

	public static void main(String args []) {
		ArrayList <Movies> movie = new ArrayList<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader("file.txt"));
			String line = "";
			while((line = in.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line.trim());
				String rating = st.nextToken();
				String name = "";
				int size = st.countTokens()-1;
				for(int i = 0; i < size; i++) {
					name += st.nextToken() + " ";
				}
				name = name.trim();
				String genre = st.nextToken();
				movie.add(new Movies(rating, name, genre));
			}

		}catch(FileNotFoundException e) {
			System.out.println("File not found");
		}catch(IOException e) {
			System.out.println("IOException!!!!!");
		}
		
		//main loop
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Do you want to search by title or genre? (enter exit to exit the program)");
			String choice = sc.nextLine();
			try {
				if(choice.equals("title")){
					Collections.sort(movie, new compareTitle());
					System.out.println("What movie title do you want to search by");
					String title = sc.nextLine();
					int index = Collections.binarySearch(movie, new Movies(null, title, null), new compareTitle());
					if(index > -1) {
						System.out.println(movie.get(index));
					}
				}
				else if(choice.equals("genre")) {
					System.out.println("What movie genre do you want to search by");
				}
				throw new NumberFormatException ();
			}catch (NumberFormatException e) {
				System.out.println("Invalid input, re-enter");
			}
		}
	}
	
}