package com.example.iatiimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iatiimd_eindoplevering.RestAPI.ApiClient;
import com.example.iatiimd_eindoplevering.RestAPI.ApiService;
import com.example.iatiimd_eindoplevering.RestAPI.tokenResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnSignin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignin=(Button) findViewById(R.id.etSignin);
        etUsername =(EditText) findViewById(R.id.etUsername);
        etPassword =(EditText) findViewById(R.id.etPassword);

        btnRegister = (Button) findViewById(R.id.login_to_register);

        //Temp om sneller in te loggen
//        etUsername.setText("s1118202@student.hsleiden.nl");
//        etPassword.setText("laravel");

        //Register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //Inloggen
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
            Call<ResponseBody> srvLogin = service.getToken(email, password, Build.MODEL);
            srvLogin.enqueue(new Callback<ResponseBody>(){
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        try {
                            String token = response.body().string();
                            tokenResponse tokenResponse = (tokenResponse) getApplicationContext();
                            tokenResponse.setAccess_token(token);

                            Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                            startActivity(intent);


                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        try {
                            String error_message = response.errorBody().string();
                            JSONObject error = new JSONObject(error_message);
                            JSONObject results = error.getJSONObject("errors");

                            JSONArray email;
                            if (results.has("email")){ email = results.getJSONArray("email"); }
                            else { email = null;}

                            JSONArray password;
                            if (results.has("password")){ password = results.getJSONArray("password"); }
                            else { password = null; }

                            if (password == null){ Toast.makeText(MainActivity.this, email.getString(0), Toast.LENGTH_SHORT).show(); }
                            else if (email == null){ Toast.makeText(MainActivity.this, password.getString(0), Toast.LENGTH_SHORT).show(); }
                            else { Toast.makeText(MainActivity.this, email.getString(0) + " " + password.getString(0), Toast.LENGTH_SHORT).show(); }

                        } catch (JSONException | IOException e) {
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