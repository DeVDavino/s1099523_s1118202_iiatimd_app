package com.example.iatiimd_eindoplevering.RestAPI;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface ApiGetUser {
    @GET("api/user")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded",
    })
    Call<ResponseBody> getUser(@Header("Authorization") String authHeader);
}
