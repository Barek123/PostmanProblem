package pl.marcb.postman.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigProperties {
    private static Properties properties;
    private static ConfigProperties configProperties = new ConfigProperties();

    private ConfigProperties() {
        properties = new Properties();

        try {
            properties.load(new FileInputStream(new File("").getAbsolutePath()+"\\src\\main\\resources\\application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigProperties getInstance() {
        return configProperties;
    }

    public String getProperties(String key){
        if(System.getProperty("dev")!=null && System.getProperty("dev").equals("true")){
            return System.getProperty(key);
        }else{
            return properties.getProperty(key);
        }
    }

    public Map<String, String> getAllProperties(){
        Map<String, String> allValues = new HashMap<>();
        properties.keySet().forEach(c -> {
            allValues.put((String)c, getProperties((String)c));
        });
        return allValues;
    }
}
