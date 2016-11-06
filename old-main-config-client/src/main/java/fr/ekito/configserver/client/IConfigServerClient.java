package fr.ekito.configserver.client;

import java.util.Properties;

public interface IConfigServerClient {

    Properties getPropertiesFromConfigServer(String application, String profile);
    
    Properties getPropertiesFromConfigServer(String application, String profile, String label);
}
