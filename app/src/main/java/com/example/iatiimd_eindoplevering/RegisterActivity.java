package com.example.iatiimd_eindoplevering;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iatiimd_eindoplevering.RestAPI.ApiClient;
import com.example.iatiimd_eindoplevering.RestAPI.ApiRegister;
import com.example.iatiimd_eindoplevering.RestAPI.ApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText username, email, password, confirm_password;
    Button register, register_to_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.register_username);
        email = (EditText) findViewById(R.id.register_email);
        password = (EditText) findViewById(R.id.register_password);
        confirm_password = (EditText) findViewById(R.id.register_confirm);
        register = (Button) findViewById(R.id.register);

        register_to_main = (Button) findViewById(R.id.register_to_login);

        //Login
        register_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //register account
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { register_account(); }
        });
    }

    private void register_account(){
        try {
            final String naam = username.getText().toString();
            final String mail = email.getText().toString();
            final String wachtwoord = password.getText().toString();
            final String wachtwoord_conf = confirm_password.getText().toString();

            Log.d("TEST", "TEST TEST TEST TEST TEST TEST " + wachtwoord + "    " + wachtwoord_conf);

            if (wachtwoord.equals(wachtwoord_conf)){
                ApiRegister service = ApiClient.getClient().create(ApiRegister.class);
                Call<ResponseBody> srvRegister = service.register(naam, mail, wachtwoord);

                srvRegister.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            } catch (Exception e){
                                e.printStackTrace();
                                Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Het wachtwoord is fout", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "System error occured :" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        } catch (Exception e){ e.printStackTrace(); }
    }
}
