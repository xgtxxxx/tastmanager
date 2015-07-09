package task.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * Utils handling string manipulation
 */
public class StringUtil {

	/**
	 * To parse string to integer
	 * 
	 * @param param
	 * string to parse
	 * @param defaultVal
	 * if string cannot be parsed correctly or it's null, use default value
	 * @return parsed integer
	 */
	public static int parseToInt(String param, int defaultVal) {
		Integer result = null;
		try {
			result = NumberUtils.createInteger(param);
			if (result == null) {
				result = defaultVal;
			}
		} catch (NumberFormatException e) {
			result = defaultVal;
		}
		return result;
	}

	/**
	 * To check if a string is empty
	 * 
	 * @param str
	 * string to be checked
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		return StringUtils.trimToNull(str) == null;
	}
	
	public static String toUpperCaseFirst(String s){
    	String ls = s.toLowerCase();
    	char[] cs = ls.toCharArray();
    	boolean flag = false;
    	cs[0] = Character.toUpperCase(cs[0]);
    	for(int i=0; i<cs.length; i++){
    		if(flag){
    			cs[i] = Character.toUpperCase(cs[i]);
    			flag = false;
    		}
    		if(cs[i]==' '){
    			flag = true;
    		}
    	}
    	return new String(cs);
    }
}
