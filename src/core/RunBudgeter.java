package core;

import display.CBBFrame;

public class RunBudgeter {
	
	public static void main(String[] args) {
//		StatementConsolidator.consolidate();
		
		CBBFrame frame = CBBFrame.init("Chase Bank Budgeter");
		frame.setVisible(true);
		
		for (int i = 0; i < 15; i++) {
			System.out.println("Babstown");
		}
	}
	
}