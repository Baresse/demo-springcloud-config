package com.example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ConfigServerService {

    @GET("{application}-{profile}.properties")
    Call<String> getProperties(@Path("application") String application, @Path("profile") String profile);

    @GET("/{label}/{application}-{profile}.properties")
    Call<String> getProperties(@Path("application") String application, @Path("profile") String profile, @Path("label") String label);
}
