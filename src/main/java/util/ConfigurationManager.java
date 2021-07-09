package util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {

    Properties configFile;


    public void setConfiguration(String configLocation) {
        configFile = new Properties();
        try (InputStream input = new FileInputStream(configLocation)) {
            configFile.load(input);
        } catch (Exception ex) {
            System.out.println("Failed to load the configuration file.");
            ex.printStackTrace();
        }

    }

    public String getProperty(String key) {
        return this.configFile.getProperty(key);
    }
}
