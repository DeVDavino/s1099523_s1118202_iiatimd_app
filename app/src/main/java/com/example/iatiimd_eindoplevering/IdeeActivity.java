package com.example.iatiimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.iatiimd_eindoplevering.RestAPI.ApiClient;
import com.example.iatiimd_eindoplevering.RestAPI.ApiGetService;
import com.example.iatiimd_eindoplevering.RestAPI.ideenResponse;
import com.example.iatiimd_eindoplevering.RestAPI.tokenResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IdeeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idee);
        recyclerView = findViewById(R.id.ideeRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();

        getIdeen();
    }

    private void getIdeen(){
        tokenResponse tokenResponse = (tokenResponse) getApplicationContext();
        final String token = "Bearer " + tokenResponse.getAccess_token();

        ApiGetService service = ApiClient.getClient().create(ApiGetService.class);
        Call<ResponseBody> srvIdeen = service.getIdeen(token);

        srvIdeen.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        String answer = response.body().string();
                        JSONArray answers = new JSONArray(answer);

                        int arrSize = answers.length();
                        Idee[] idees = new Idee[arrSize];

                        for (int i = 0; i < arrSize; i++) {
                            JSONObject idee = answers.getJSONObject(i);
                            idees[i] = new Idee(idee.getString("titel"),idee.getString("description"),idee.getString("categorie"));
                        }

                        recyclerViewAdapter = new IdeeAdapter(idees);
                        recyclerView.setAdapter(recyclerViewAdapter);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}