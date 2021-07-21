package com.example.iatiimd_eindoplevering.RestAPI;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiRegister {
    @FormUrlEncoded
    @POST("api/register")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded",
    })
    Call<ResponseBody> register(@Field("name") String name,
                                @Field("email") String email,
                                @Field("password") String password);
}
