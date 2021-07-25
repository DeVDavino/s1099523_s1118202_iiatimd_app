package com.example.iatiimd_eindoplevering.RestAPI;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiUpdateIdee {
    @FormUrlEncoded
    @PUT("api/ideen/update/{id}")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded",
    })
    Call<ResponseBody> updateIdee(@Field("titel") String titel,
                                @Field("description") String description,
                                @Field("categorie") String categorie,
                                @Path("id") int id,
                                @Header("Authorization") String authHeader);
}
