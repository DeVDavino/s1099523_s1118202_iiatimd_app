package com.example.iatiimd_eindoplevering.RestAPI;

import android.app.Application;

import com.google.gson.annotations.SerializedName;

public class tokenResponse extends Application {
    @SerializedName("access_token")
    private String access_token;

    @SerializedName("token_type")
    private String token_type;

    public tokenResponse() { }

    public void setAccess_token(String access_token) { this.access_token = access_token; }

    public void setToken_type(String token_type) { this.token_type = token_type; }


    public String getAccess_token() { return access_token; }

    public String getToken_type() { return token_type; }
}
