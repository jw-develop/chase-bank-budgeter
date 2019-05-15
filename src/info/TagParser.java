package info;

/**
 * Houses static class to parse out to a tag.
 * @author jameswhite
 *
 */
public class TagParser {
	
	/**
	 * Parses a description into parts.
	 * @param desc Unparsed description.
	 * @return Description,City,State,Tag
	 */
	public static String[] parse(String desc) {
		
		desc = desc.replaceAll(" +"," ");
		String[] b_desc = desc.split(" ");
		String new_desc = "";
		String city = "";
		String state = "";
		String tag = "";
		
		int i = 0;
		for (i=0;i < b_desc.length && !b_desc[i].equals("");i++);
		
		// If valid desc
		if (i > 2) {
			state = b_desc[i-2];
			new_desc += b_desc[0];
			for (int j=1;j<i-2;j++)
				new_desc +=" "+b_desc[j];
			
			// Trim off parentheses.
			if (new_desc.charAt(0) == '"')
				new_desc = new_desc.substring(1);
		}
		else
			tag = "invalid";
		return new String[] {new_desc,city,state,tag};
	}
}
