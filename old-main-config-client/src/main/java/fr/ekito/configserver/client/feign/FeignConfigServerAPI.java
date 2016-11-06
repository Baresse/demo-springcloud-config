package fr.ekito.configserver.client.feign;

import feign.Param;
import feign.RequestLine;

public interface FeignConfigServerAPI {

    @RequestLine("GET /{application}-{profile}.properties")
    String getProperties(@Param("application") String application, @Param("profile") String profile);

    @RequestLine("GET /{label}/{application}-{profile}.properties")
    String getProperties(@Param("application") String application, @Param("profile") String profile, @Param("label") String label);
}
