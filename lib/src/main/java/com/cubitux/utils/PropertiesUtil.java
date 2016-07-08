package com.cubitux.utils;

import com.cubitux.MyMain;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by pierre on 2016-05-13.
 */
public class PropertiesUtil {

    /**
     * Get value of a key from app.properties
     *
     * @param key
     * @return value or null (if no value were found)
     * @throws IOException
     */
    public static String getValue(String key) throws IOException {

        Properties prop = new Properties();

        //load a properties file from class path, inside static method
        prop.load(MyMain.class.getClassLoader().
                getResourceAsStream("app.properties"));

        //get the property value and print it out
        String value = prop.getProperty(key);

        return value;
    }

    /**
     * Set value of a key into app.properties
     *
     * @param key   Key to be save
     * @param value Value of the key
     * @throws IOException
     */
    public static void setValue(String key, String value) throws IOException {
        Properties prop = new Properties();

        //load a properties file from class path, inside static method
        prop.load(MyMain.class.getClassLoader().
                getResourceAsStream("app.properties"));

        //set the property value and print it out
        prop.setProperty(key, value);

    }
}
