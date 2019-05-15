package info;

/**
 * Builds a transaction from a line read in from a .csv that was
 * pulled from the chase database.
 * @author jameswhite
 *
 */
public class Transaction {
	
	public final String date;
	public final String amount;
	public final String desc;
	public final String location;
	public final Tag tag;
	
	public Transaction(String inp) {
		String[] input = inp.split(",");
		date = input[1];
		amount = input[3];
		
		input[2] = input[2].replaceAll(" +"," ");
		String[] b_desc = input[2].split(" ");
		String new_desc = "";
		String new_loc = "";
		
		int i = 0;
		for (i=0;i < b_desc.length && !b_desc[i].equals("");i++);
		
		// If valid desc
		if (i > 2) {
			new_loc = b_desc[i-2];
			new_desc += b_desc[0];
			for (int j=1;j<i-2;j++)
				new_desc +=" "+b_desc[j];
			
			// Trim off parentheses.
			if (new_desc.charAt(0) == '"')
				new_desc = new_desc.substring(1);
		}
		else
			if (!input[2].equals("Description"))
				System.err.println("Bad desc "+input[2]);
		
		
		location = new_loc;
		desc = new_desc;
	}
	
	public String toString() {
		String toReturn = "";
		
		toReturn += "Date,amt: "+date;
		toReturn += ","+amount+"\n";
		toReturn += "Location: "+location+"\n";
		toReturn += "Desc: "+desc+"\n";
		
		return toReturn;
	}
	
	public String toRow() {
		String toReturn = "";
		
		String[] row = {date,amount,location,desc};
		
		for (String s : row)
			toReturn += s+",";
		
		return toReturn;
	}
}