package core;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Class that houses all configuration options as read in by
 * config.txt.
 * 
 * @author jameswhite
 *
 */
public class Config {
	
	private static Map<String,String> options;
	
	private static void populateOptions() throws IOException {
		
		options = new HashMap<>();
		Scanner config = new Scanner(Paths.get("config.txt"));
		while (config.hasNext()) {
			String line = config.nextLine();
			String[] words = line.split(":");
			if (words.length > 1)
				options.put(words[0],words[1]);
		}

		config.close();
	}
	
	public static String option(String key) {
		if (options == null)
			
			try {
				populateOptions();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		if (options.containsKey(key))
			return options.get(key);
		
		System.err.println(key+" not found");
		return null;
	}
}
