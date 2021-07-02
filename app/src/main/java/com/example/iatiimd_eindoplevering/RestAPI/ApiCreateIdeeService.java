package com.example.iatiimd_eindoplevering.RestAPI;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiCreateIdeeService {
    @FormUrlEncoded
    @POST("api/ideen/create")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded",
    })
    Call<ResponseBody> postIdee(@Field("titel") String titel,
                                @Field("description") String description,
                                @Field("categorie") String categorie,
                                @Header("Authorization") String authHeader);
}
