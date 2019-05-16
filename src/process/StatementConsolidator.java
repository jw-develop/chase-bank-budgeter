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

/**
 * Uses config file to find location of Chase bank statements, then
 * consolidates .csv files into master_statement.csv file, which is
 * written just above the raw files location.
 * 
 * csv scheme is 
 * 
 * date,amount,city:state,description,
 * 
 * @author jameswhite
 *
 */
public class StatementConsolidator {
	
	private static String source;

	public static void consolidate() {
		try {
			genMasterStatement();
//			genMonthlyStatements();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void genMasterStatement() throws FileNotFoundException {
		source = Config.option("statements");
		
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
//				else
//					System.err.println(t.toRow());
			}
			
			sca.close();
		}
		
		// Write to file.
		String toWrite = "";
		for (Transaction t : trans)
			toWrite += t.toRow()+"\n";
		Path path = Paths.get(source+"master_statement.csv");
		write(toWrite,path);
	}
	
	private static void write(String toWrite,Path path) {
		try {
			//Make and write to the file.
			Files.deleteIfExists(path);
			Files.createFile(path);
			Files.write(path, toWrite.getBytes());
		}
		catch (IOException e) {e.printStackTrace();}
	}
}