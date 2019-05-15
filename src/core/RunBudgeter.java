package core;

import java.io.FileNotFoundException;

import process.StatementConsolidator;

public class RunBudgeter {
	
	public static void main(String[] args) {
		
		try {
			StatementConsolidator.consolidate();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}