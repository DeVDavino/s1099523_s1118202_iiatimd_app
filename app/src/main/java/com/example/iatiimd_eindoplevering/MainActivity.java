package com.example.iatiimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iatiimd_eindoplevering.RestAPI.ApiClient;
import com.example.iatiimd_eindoplevering.RestAPI.ApiService;
import com.example.iatiimd_eindoplevering.RestAPI.tokenResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignin=(Button) findViewById(R.id.etSignin);
        etUsername =(EditText) findViewById(R.id.etUsername);
        etPassword =(EditText) findViewById(R.id.etPassword);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallLoginService();
            }
        });
    }

    private void CallLoginService(){
        try {
            final String email = etUsername.getText().toString();
            final String password = etPassword.getText().toString();

            ApiService service = ApiClient.getClient().create(ApiService.class);
            Call<ResponseBody> srvLogin = service.getToken(email, password, "Pixel 2");
            srvLogin.enqueue(new Callback<ResponseBody>(){
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        try {
                            String token = response.body().string();
                            tokenResponse tokenResponse = new tokenResponse();
                            tokenResponse.setAccess_token(token);
                            Toast.makeText(MainActivity.this, tokenResponse.getAccess_token(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, "Token got successfully", Toast.LENGTH_SHORT).show();

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t){
                    Toast.makeText(MainActivity.this, "System error occured :" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "System error occured, please check your internet connection is enabled", Toast.LENGTH_SHORT).show();

        }
    }
}