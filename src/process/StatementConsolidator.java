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
			
			// Moving past title stuff line.
			if (sca.hasNext())
				sca.nextLine();
			else
				System.err.println("Invalid file");
			
			// Reading out all transactions.
			while (sca.hasNext()) {
				Transaction t = new Transaction(sca.nextLine());
				if (!t.tag().equals("invalid"))
					trans.add(t);
				else
					System.err.println(t);
			}
			
//			for (Transaction t : trans)
//				System.out.println(t);
			
			sca.close();
		}
	}
}