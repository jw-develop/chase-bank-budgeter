package core;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class RunBudgeter {
	
	public static String statements_location;
	
	public static void main(String[] args) {
		
		try {
			readConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void readConfig() throws IOException {
		
		Scanner config = new Scanner(Paths.get("config.txt"));
		
		while (config.hasNext()) {
			String line = config.nextLine();
			String[] words = line.split(":");
			
			// Statements config section.
			if (words.length > 1 && words[0].equals("statements"));
					statements_location = words[1];
		}
		
		config.close();
	}
	
}