package fr.ekito.configserver.client;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public class FeignConfigServerClient implements IConfigServerClient {

    final private FeignConfigServerAPI api;

    /**
     * Constructor
     *
     * @param configServerUrl configuration server endpoint URL
     */
    public FeignConfigServerClient(String configServerUrl) {
        this(configServerUrl, null, null);
    }

    /**
     * Constructor
     *
     * @param configServerUrl configuration server endpoint URL
     * @param username        username to be used for accessing the configuration server (mainly for production profile)
     * @param password        password to be used for accessing the configuration server (mainly for production profile)
     */
    public FeignConfigServerClient(String configServerUrl, String username, String password) {


        Feign.Builder builder = Feign.builder();
        
        if (username != null && password != null) {
            builder.requestInterceptor(new BasicAuthRequestInterceptor(username, password));
        }
        
        api = builder
                .requestInterceptor(System.out::println)
                .target(FeignConfigServerAPI.class, configServerUrl);
    }

    /**
     * Retrieve list of properties from the configuration server.
     *
     * @param application application name
     * @param profile     application profile such as dev, production, etc.
     * @return Retrieved properties
     */
    @Override
    public Properties getPropertiesFromConfigServer(String application, String profile) {
        return getPropertiesFromConfigServer(application, profile, null);
    }

    /**
     * Retrieve list of properties from the configuration server.
     *
     * @param application application name
     * @param profile     application profile such as dev, production, etc.
     * @param label       application label such as version1, etc.
     * @return Retrieved properties
     */
    @Override
    public Properties getPropertiesFromConfigServer(String application, String profile, String label) {

        String body;
        if (label != null) {
            body = api.getProperties(application, profile, label);
        } else {
            body = api.getProperties(application, profile);
        }

        Properties properties = new Properties();
        try {
            if (body != null) {
                properties.load(new StringReader(body));
            }
        } catch (IOException ignored) {
        }
        return properties;
    }
}
