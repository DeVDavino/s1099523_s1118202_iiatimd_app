package com.example.iatiimd_eindoplevering.RestAPI;

import android.app.Application;

import com.example.iatiimd_eindoplevering.Idee;
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

    private int arrSize;
    private Idee[] idees = new Idee[arrSize];

    public int getArrSize() {
        return arrSize;
    }

    public void setArrSize(int arrSize) {
        this.arrSize = arrSize;
    }

    public Idee[] getIdees() {
        return idees;
    }

    public void setIdees(Idee[] idees) {
        this.idees = idees;
    }
}
