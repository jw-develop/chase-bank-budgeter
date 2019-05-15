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
			
			// Moving past titles line.
			if (sca.hasNext())
				sca.nextLine();
			else
				System.err.println("Invalid file");
			
			// Examining all transactions.
			int i = 0;
			while (sca.hasNext()) {
				Transaction t = new Transaction(sca.nextLine());
				if (!t.tag().equals("invalid")) {
					trans.add(t);
					System.out.println(t.toRow());
				}
//				else
//					System.err.println(t);
				
				// TEMPORARY
				if (i > 15)
					break;
				i++;
			}
			
			sca.close();
		}
	}
}