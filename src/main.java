import java.io.*;
import java.util.*;

public class main {

	public static void main(String args []) {
		ArrayList <movies> movie = new ArrayList<>();
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
				movie.add(new movies(rating, name, genre));
			}
			
		}catch(FileNotFoundException e) {
			System.out.println("File not found");
		}catch(IOException e) {
			System.out.println("IOException!!!!!");
		}
	}
	
}