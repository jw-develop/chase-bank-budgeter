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
	
	public Transaction(String inp) {
		String[] input = inp.split(",");
		date = input[1];
		amount = input[3];
		
		String[] b_desc = input[2].split(" ");
		String new_desc = "";
		String new_loc = "";
		
		int i = 0;
		for (i=0;i < b_desc.length && !b_desc[i].equals("");i++);
		
		// If valid desc
		if (i > 2) {
			new_loc = b_desc[i-2]+","+b_desc[i-1];
			new_desc += b_desc[0];
			for (int j=1;j<i-2;j++)
				new_desc +=" "+b_desc[j];
		}
		else
			System.err.println("Bad desc "+input[2]);
		
		// Trim off parentheses.
		if (new_desc.charAt(0) == '"')
			new_desc = new_desc.substring(1);
		
		location = new_loc;
		desc = new_desc;
		System.out.println(this);
	}
	
	public String toString() {
		String toReturn = "";
		
		toReturn += "Date,amt: "+date;
		toReturn += ","+amount+"\n";
		toReturn += "Location: "+location+"\n";
		toReturn += "Desc: "+desc+"\n";
		
		return toReturn;
	}
}