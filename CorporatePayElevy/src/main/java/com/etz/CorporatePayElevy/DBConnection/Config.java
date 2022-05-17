package com.etz.CorporatePayElevy.DBConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *
 * @author rhema.adedipe
 */
public class Config {

	private Config() {
	}

	final static Properties prop = new Properties();
	private static final Logger log;

	static {

		log = Logger.getLogger(Config.class.getName());

		InputStream input = null;
		try {
		

			String fpath = "cfg\\CorperatePayElevy.properties";
		
			input = new FileInputStream(new File(fpath));
			prop.load(input);
		} catch (IOException ex) {
		
			Config.log.info("\"Exception:: \" + ex.getMessage());->PROCESS EXCEPTION=>" + ex.getMessage());

		}

		finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
				
					Config.log.info("\"Exception:: \" + ex.getMessage());->CONFIG EXCEPTION=>" + e.getMessage());

				}
			}
		}

	}

	public static String getValue(final String key) {

		return prop.getProperty(key);

	}
}
