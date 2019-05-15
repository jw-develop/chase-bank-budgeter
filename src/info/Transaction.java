package info;

/**
 * Builds a transaction from a line read in from a .csv that was
 * pulled from the chase database.
 * @author jameswhite
 */
public class Transaction {

	private String date;
	private String amount;
	private String desc;
	private String location;
	
	// Represents the sort of transaction that this is.
	private String tag;
	
	public Transaction(String inp) {
		String[] input = inp.split(",");
		
		date = input[1];
		amount = input[3];
		
		// Parsed Description,City,State,Tag
		String[] p_desc = TagParser.parse(input[2]);
		
		desc = p_desc[0];
		location = p_desc[1]+":"+p_desc[2];
		tag = p_desc[3];
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

	public String date() {
		return date;
	}
	
	public String amount() {
		return amount;
	}

	public String desc() {
		return desc;
	}

	public String location() {
		return location;
	}

	public String tag() {
		return tag;
	}
}