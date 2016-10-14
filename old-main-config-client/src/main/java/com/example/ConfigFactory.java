package com.example;

import retrofit2.Call;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConfigFactory {

    /**
     * Return Properties instance built from the retrieved properties from the config server and from the provided 
     * ResourceBundle. 
     * 
     * @param rb main application resource bundle
     * @param profile application profile such as dev, production, etc.
     * @param label application label such as version1, etc.
     * @return Properties instance built from the retrieved properties from the config server and from the provided 
     * ResourceBundle. 
     */
    public static Properties getConfiguration(ResourceBundle rb, String profile, String label) {
        
        String configServerUrl = getProperty("config.server.url", rb);
        String appName = getProperty("application.name", rb);
        String username = getProperty("config.server.username", rb);
        String password = getProperty("config.server.password", rb);
        
        Properties properties = new Properties();
        
        // First, try to retrieve properties from the configuration server
        if (configServerUrl != null && appName != null) {
            properties = ConfigFactory.getPropertiesFromConfigServer(configServerUrl, appName, profile, label, username, password);
        }
        
        // Then, add ResourceBundle properties if not already defined in the returned Properties to the result to be returned.
        final Properties finalProperties = properties;
        rb.keySet().forEach(s -> finalProperties.putIfAbsent(s, rb.getString(s)));

        return properties;
    }

    /**
     * Return Properties instance built from the retrieved properties from the config server and from the provided 
     * ResourceBundle. 
     *
     * @param rb main application resource bundle
     * @param profile application profile such as dev, production, etc.
     * @return Properties instance built from the retrieved properties from the config server and from the provided 
     * ResourceBundle. 
     */
    public static Properties getConfiguration(ResourceBundle rb, String profile)  {
        return getConfiguration(rb, profile, null);
    }

    /**
     * Retrieve list of properties from the configuration server.
     *
     * @param configServerUrl configuration server endpoint URL
     * @param application application name 
     * @param profile application profile such as dev, production, etc.
     * @param label application label such as version1, etc.
     * @param username username to be used for accessing the configuration server (mainly for production profile)
     * @param password password to be used for accessing the configuration server (mainly for production profile)
     * @return Retrieved properties
     */
    private static Properties getPropertiesFromConfigServer(String configServerUrl, String application, String profile, String label, String username, String password) {
        ConfigServerService api = ServiceGenerator.createService(configServerUrl, ConfigServerService.class, username, password);

        Call<String> call;
        if (label != null) {
            call = api.getProperties(application, profile, label);
        } else {
            call = api.getProperties(application, profile);
        }

        Properties properties = new Properties();
        try {
            String body = call.execute().body();
            if (body != null) {
                properties.load(new StringReader(body));
            }
        } catch (IOException ignored) {
        }
        return properties;
    }

    /**
     * Retrieve property from System properties first and if not defined, try to retrieve it from the ResourceBundle.
     * Finally if not defined there, return <code>null</code>.
     *
     * @param key property name
     * @param rb  main application resource bundle
     * @return Found property value or <code>null</code>
     */
    private static String getProperty(String key, ResourceBundle rb) {
        String property = System.getProperty(key);
        if (property == null) {
            property = rb.getString(key);
        }
        return property;
    }
}
