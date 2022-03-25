import java.io.*;
import java.util.Random;

public class testfilegenerator {

	public static void main(String[] args) throws IOException {

		PrintWriter pw = new PrintWriter(new FileWriter("file.txt"));

		for (int i = 0; i < 100; i++) {
			double rand = Math.round(((Math.random() * (100)) + 1) * 10.0) / 10.0;
			String temp = getSaltString();
			String name = "";
			if (rand < 50) {
				name = temp.substring(0, 8) + " " + temp.substring(8);
			}
			if(rand % 2 == 0) {
				name = temp.substring(0, 8) + " " + temp.substring(8);
			}
			if(rand < 25) {
				name = temp.substring(0, 8) + " " + temp.substring(8);
			}
			else {
				name = temp;
			}
			System.out.println(name);
		}

	}

	public static String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

}
