package process;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import core.Config;
import info.Transaction;

public class StatementConsolidator {
	
	public static void consolidate() throws FileNotFoundException {
		
		File[] files = new File(Config.option("statements")).listFiles();
		
		
		Scanner sca = new Scanner(files[0]);
		sca.nextLine();
		Transaction trans = new Transaction(sca.nextLine());
	}
	
}