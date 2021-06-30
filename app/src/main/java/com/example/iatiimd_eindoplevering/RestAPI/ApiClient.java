package com.example.iatiimd_eindoplevering.RestAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://172.28.71.98:80";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            // Create a new object from HttpLoggingInterceptor
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Add Interceptor to HttpClient
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(600, TimeUnit.SECONDS)
                    .connectTimeout(600, TimeUnit.SECONDS)
                    .addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client) // Set HttpClient to be used by Retrofit
                    .build();
        }
        return retrofit;
    }
}
