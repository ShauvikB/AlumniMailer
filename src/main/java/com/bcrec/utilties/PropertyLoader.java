package com.bcrec.utilties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
	
	public Properties loadProperties() {
		
		Properties prop = new Properties();
		
		try (InputStream input = getClass()
				.getClassLoader().getResourceAsStream("Allumni.properties");) {

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("file_path"));
            System.out.println(prop.getProperty("username"));
            System.out.println(prop.getProperty("password"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
		return prop;

	}

}
