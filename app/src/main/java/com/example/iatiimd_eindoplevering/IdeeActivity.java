package com.example.iatiimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.iatiimd_eindoplevering.RestAPI.ApiClient;
import com.example.iatiimd_eindoplevering.RestAPI.ApiGetService;
import com.example.iatiimd_eindoplevering.RestAPI.tokenResponse;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IdeeActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase db;
    private Idee[] data;

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        //MultiDex.install(this); //comment this
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idee);
        recyclerView = findViewById(R.id.ideeRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();
        db = AppDatabase.getInstance(this.getApplicationContext());
        getIdeen();
//        new Thread(new InsertIdeeTask(db,ideee[0])).start();


//        db.ideeDAO().getAll().get(0).getDescription();
//        db.ideeDAO().getAll().get(0).getId();
//        db.ideeDAO().getAll().get(0).getCategorie();
//        Log.d("testing", "Statement Reached" );
//        Log.d("dbtest", title);
//        String idee = AppDatabase.setIdee("Hello world");
//        Log.d("testing_one", idee);

//        idee = db.ideeDAO().getAll().get(0).getTitle();

//        new Thread(new GetIdeeTask(db)).start();


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
                            idees[i] = new Idee(idee.getString("titel"),idee.getString("description"),idee.getString("categorie"), idee.getInt("id"));
                        }
                        tokenResponse.setArrSize(arrSize);
                        tokenResponse.setIdees(idees);

                        recyclerViewAdapter = new IdeeAdapter(idees);
                        recyclerView.setAdapter(recyclerViewAdapter);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                    Idee[] idees = tokenResponse.getIdees();

                    recyclerViewAdapter = new IdeeAdapter(idees);
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}