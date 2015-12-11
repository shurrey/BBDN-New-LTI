package bbdn.sample.lti11.util;

import java.util.HashMap;

public abstract class HashMapConverter {

	/**
	 * Converts a HashMap.toString() back to a HashMap
	 * @param text
	 * @return HashMap<String, String>
	 */
	public static HashMap<String,String> convertToStringToHashMap(String text){
		HashMap<String,String> data = new HashMap<String,String>();
		
		if(text.startsWith("{")) {
			text = text.substring(1,text.lastIndexOf('}'));
		}
		
		String[] split = text.split("\r\n");
		for (int t=0; t < split.length; t++) {
			String[] xsplit = split[t].split("=");
			data.put(xsplit[0], xsplit[1]);
		}
		
		return data;
	}
	
	/**
	 * Prepares a HashMap.toString() from the database into display friendly String for the config page
	 * @param csv
	 * @return String
	 */
	public static String convertHashMapCsvToDisplay(String csv) {
		// We save HashMap.toString() to the database. This adds {} around the entire thing 
		// and replaces the carriage returns with commas (i.e {extra1=bob, extra2=fishsticks}; so we:
		
		//replace all occurrences of "' " with a carriage return and a line feed; and then...
		csv = csv.replace(", ","\r\n");
		//strip the curly brackets off each end
		csv = csv.substring(1, csv.length()-1);
		
		return csv;
	}
	
}
