package core;

import java.io.FileNotFoundException;

import process.StatementConsolidator;

public class RunBudgeter {
	
	public static void main(String[] args) {
		
		System.out.println(Config.option("statements"));
		
		try {
			StatementConsolidator.consolidate();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}