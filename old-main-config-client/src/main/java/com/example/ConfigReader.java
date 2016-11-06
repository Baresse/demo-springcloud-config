package com.example;

import fr.ekito.configserver.client.feign.FeignConfigServerClient;
import fr.ekito.configserver.client.IConfigServerClient;

import java.util.Properties;
import java.util.ResourceBundle;

public class ConfigReader {
    
    /**
     * Return Properties instance built from the retrieved properties from the config server and from the provided
     * ResourceBundle.
     *
     * @param rb      main application resource bundle
     * @param profile application profile such as dev, production, etc.
     * @param label   application label such as version1, etc.
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
            IConfigServerClient client = new FeignConfigServerClient(configServerUrl, username, password);
            properties = client.getPropertiesFromConfigServer(appName, profile, label);
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
     * @param rb      main application resource bundle
     * @param profile application profile such as dev, production, etc.
     * @return Properties instance built from the retrieved properties from the config server and from the provided
     * ResourceBundle.
     */
    public static Properties getConfiguration(ResourceBundle rb, String profile) {
        return getConfiguration(rb, profile, null);
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
            try {
                property = rb.getString(key);
            } catch (MissingResourceException ignored) {}
        }
        return property;
    }
}
