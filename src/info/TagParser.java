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
	private static String[] locations = {
			"wheaton il",
			"glen ellyn il",
			"carol stream il",
			"pine city mn"
	};
	private static String c_desc;
	
	/**
	 * Most important section.
	 * Each entry contains the name of the tag, the regex that it
	 * matches to, and the indices of the groups that correspond to
	 * DESCRIPTION, CITY, and STATE, respectively.
	 */
	static {
		String endDate = "\\d\\d\\/\\d\\d[\"]";
		convert(
				"normal",
				"[\"](.+)\\s(\\D\\D)\\s"+endDate,
				1,2
		);
//		convert(
//				"aldi",
//				"(.+)\\s(\\w+)\\s(\\D\\D)\\s(.+)[\"]",
//				0,1,2
//		);
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
	private static void convert(String tag,String regex,int a,int b) {
		parserList.add(desc -> {
			String[] toReturn = null;
			
			Matcher m = Pattern.compile(regex).matcher(c_desc);
			
			if (m.matches()) {
				toReturn =  new String[] {
						m.group(a),
						"UNKNOWN",
						m.group(b),
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
		String loc = "UNKNOWN";
		
		// Check for cities
		for (String l : locations) {
			l = l.toUpperCase();
			Matcher m = Pattern.compile("(.+)("+l+")(.+)").matcher(c_desc);
			if (m.matches()) {
				loc = l.substring(0,l.length()-3);
				desc = desc.replace(loc,"");
			}
		}
		
		// Checks all parsing schemes and returns if valid.
		for (Parser tP : parserList) {
			toReturn = tP.data(desc);
			if (!(toReturn == null)) {
				toReturn[1] = loc;
				return toReturn;
			}
		}
		
		// No match found at all.
		return new String[] {c_desc,"","","invalid"};
	}
}
