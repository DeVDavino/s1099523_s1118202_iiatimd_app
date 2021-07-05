package com.example.iatiimd_eindoplevering.RestAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    /*
    HOW TO GET BASE_URL
    1. open ubuntu
    2. run the following command: ip a
    3. get the ip from inet in by 4: eth0, NOT inet6
    4. go to the folder where the project is (API/backend)
    5. run the following command: sudo php artisan serve --host THE_IP_FROM_INET --port 80
    NOTE: the port 80 is there to prevent laravel from trying to start the server 10 times if you're ip is wrong

    And don't forget to change the BASE_URL below ↓↓↓
     */
    public static final String BASE_URL = "http://172.17.46.135:80";
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
