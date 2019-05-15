package process;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import core.Config;
import info.Transaction;

public class StatementConsolidator {
	
	public static void consolidate() throws FileNotFoundException {
		
		File[] files = new File(Config.option("statements")).listFiles();
		
		List<Transaction> trans = new ArrayList<>();
		
		for (File f : files) {
			Scanner sca = new Scanner(f);
			
			while (sca.hasNext()) {
				trans.add(new Transaction(sca.nextLine()));
			}
			
			for (Transaction t : trans)
				System.out.println(t);
			
			sca.close();
		}
	}
	
}