package info;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Houses static class to parse out to a tag.
 * @author jameswhite
 *
 */
public class TagParser {
	
	interface Parser {String[] data(String desc);}
	private static List<Parser> parserList = new ArrayList<>();
	private static String c_desc;
	
	/**
	 * Most important section.
	 * Each entry contains the name of the tag, the regex that it
	 * matches to, and the indices of the groups that correspond to
	 * DESCRIPTION, CITY, and STATE, respectively.
	 */
	static {
		convert(
				"wheaton",
				"(.*)(WHEATON)\\s(IL)(.*)",
				0,1,2
		);
		convert(
				"aldi",
				"(.*)(ALDI)(.*)(IL)(.*)",
				0,1,2
		);
	}
	
	/**
	 * Method to populate parserList, which will interpret data sent
	 * by parse() and return a list of relevant data.
	 * 
	 * @param tag Tag to attach.
	 * @param regex Pattern to test for.
	 * @param a Description index.
	 * @param b City index.
	 * @param c State index.
	 */
	private static void convert(String tag,String regex,int a,int b,int c) {
		parserList.add(desc -> {
			String[] toReturn = null;
			
			Matcher m = Pattern.compile(regex).matcher(c_desc);
			
			if (m.matches()) {
				toReturn =  new String[] {
						m.group(a),
						m.group(b),
						m.group(c),
						tag
				};
			}

			return toReturn;
		});
	}

	/**
	 * Parses a description into parts.
	 * @param desc Unparsed description.
	 * @return Description,City,State,Tag
	 */
	public static String[] parse(String desc) {
		String[] toReturn;
		
		// Kill extra whitespace. Should this be here?
		desc = desc.replaceAll(" +"," ");
		c_desc = desc;
		
		// Checks all parsing schemes and returns if valid.
		for (Parser tP : parserList) {
			toReturn = tP.data(desc);
			if (!(toReturn == null))
				return toReturn;
		}
		
		// No match found at all.
		return new String[] {c_desc,"","","invalid"};
	}
}
