package process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import core.Config;
import info.Transaction;

public class StatementConsolidator {
	
	public static void consolidate() throws FileNotFoundException {
		
		String source = Config.option("statements");
		
		File[] files = new File(source).listFiles();
		
		List<Transaction> trans = new ArrayList<>();
		
		for (File f : files) {
			Scanner sca = new Scanner(f);
			
			// Moving past titles line.
			if (sca.hasNext())
				sca.nextLine();
			else
				System.err.println("Invalid file");
			
			// Examining all transactions.
			while (sca.hasNext()) {
				Transaction t = new Transaction(sca.nextLine());
				if (!t.tag().equals("invalid"))
					trans.add(t);
				else
					System.err.println(t.toRow());
			}
			
			sca.close();
		}
		
		String toWrite = "";
		Path path = Paths.get(source+"../master_statement.csv");
		
		for (Transaction t : trans) {
			System.out.println(t);
			toWrite += t.toRow()+"\n";
		}
		
		try {
			//Make and write to the file.
			Files.deleteIfExists(path);
			Files.createFile(path);
			Files.write(path, toWrite.getBytes());
		}
		catch (IOException e) {e.printStackTrace();}
	}
}