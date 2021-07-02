package com.example.iatiimd_eindoplevering;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iatiimd_eindoplevering.RestAPI.ApiClient;
import com.example.iatiimd_eindoplevering.RestAPI.ApiCreateIdeeService;
import com.example.iatiimd_eindoplevering.RestAPI.ApiService;
import com.example.iatiimd_eindoplevering.RestAPI.tokenResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateIdeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText etTitel, etDescription;
    Spinner sCategorie;
    Button btnCreate;
    String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_idee);

        btnCreate=(Button) findViewById(R.id.create);
        etTitel =(EditText) findViewById(R.id.titel);
        etDescription =(EditText) findViewById(R.id.description);

        //create the spinner
        sCategorie = (Spinner) findViewById(R.id.categorie);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCategorie.setAdapter(adapter);
        sCategorie.setOnItemSelectedListener(this);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create();
            }
        });
    }

    //create idee
    private void Create(){
        //information that is saved in the database
        final String titel = etTitel.getText().toString();
        final String description = etDescription.getText().toString();
        final String categorie = selected;

        //token to verify the user
        tokenResponse tokenResponse = (tokenResponse) getApplicationContext();
        final String token = "Bearer " + tokenResponse.getAccess_token();

        ApiCreateIdeeService service = ApiClient.getClient().create(ApiCreateIdeeService.class);
        Call<ResponseBody> srvCreate = service.postIdee(titel, description, categorie, token);
        srvCreate.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Toast.makeText(CreateIdeeActivity.this, "Gelukt", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CreateIdeeActivity.this, IdeeActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    //Spinner item select
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected = parent.getItemAtPosition(position).toString();
    }
    //Spinner no item selected
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
