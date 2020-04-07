package com.mashibing;

import java.io.IOException;
import java.util.Properties;

/**
 * @create: 2020-04-03 13:21
 **/
public class ConfigMgr {


    private ConfigMgr(){}

    static Properties properties = new Properties();

    static {
        try {
            properties.load(ConfigMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer getInt(String key){
        if(properties == null) return null;
        return Integer.parseInt(properties.getProperty(key));
    }

    public static String getString(String key){
        if(properties == null) return null;
        return properties.getProperty(key);
    }
}
