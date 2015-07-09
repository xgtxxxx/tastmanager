package task.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	private static InputStream inStream;
    private static Properties pro;
    
    static{
        inStream=PropertiesReader.class.getResourceAsStream("/config.properties");
        pro=new Properties();
        try {
            pro.load(inStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getProperty(String key){
    	return pro.getProperty(key);
//    	return "20:00:00";
    }
}
