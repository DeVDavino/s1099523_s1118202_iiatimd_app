package com.example.iatiimd_eindoplevering;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iatiimd_eindoplevering.RestAPI.ApiClient;
import com.example.iatiimd_eindoplevering.RestAPI.ApiDeleteIdee;
import com.example.iatiimd_eindoplevering.RestAPI.ApiGetService;
import com.example.iatiimd_eindoplevering.RestAPI.ApiUpdateIdee;
import com.example.iatiimd_eindoplevering.RestAPI.tokenResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditIdeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText etTitel, etDescription;
    Spinner sCategorie;
    Button btnEdit, btnDelete;
    String selected;
    int id;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_idee);

        btnEdit = (Button) findViewById(R.id.edit);
        btnDelete = (Button) findViewById(R.id.delete);
        etTitel = (EditText) findViewById(R.id.edit_titel);
        etDescription = (EditText) findViewById(R.id.edit_description);

        //create the spinner
        sCategorie = (Spinner) findViewById(R.id.edit_categorie);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCategorie.setAdapter(adapter);
        sCategorie.setOnItemSelectedListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        if (bundle != null){
            String title = (String) bundle.get("titel");
            String description = (String) bundle.get("description");
            String categorie = (String) bundle.get("categorie");
            id = (int) bundle.get("id");

            etTitel.setText(title);
            etDescription.setText(description);
            sCategorie.setSelection(adapter.getPosition(categorie));
        }

        //Roept de functie delete aan als er op de knop btnDelete geklickt wordt
        btnDelete.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { delete(); } });

        //Roept de functie edit aan als er op de knop btnEdit geklickt wordt
        btnEdit.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { edit(); } }); }

    //Spinner selected item
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected = parent.getItemAtPosition(position).toString();
    }

    //Spinner no item selected
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void delete(){
        tokenResponse tokenResponse = (tokenResponse) getApplicationContext();
        final String token = "Bearer " + tokenResponse.getAccess_token();

        ApiDeleteIdee service = ApiClient.getClient().create(ApiDeleteIdee.class);
        Call<ResponseBody> srvIdeen = service.deleteIdee(id, token);

        srvIdeen.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Toast.makeText(EditIdeeActivity.this, "Het idee is succesvol verwijderd", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(EditIdeeActivity.this, NavigationActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void edit(){
        tokenResponse tokenResponse = (tokenResponse) getApplicationContext();
        final String token = "Bearer " + tokenResponse.getAccess_token();

        final String titel = etTitel.getText().toString();
        final String description = etDescription.getText().toString();
        final String categorie = selected;

        ApiUpdateIdee service = ApiClient.getClient().create(ApiUpdateIdee.class);
        Call<ResponseBody> srvIdeen = service.updateIdee(titel, description, categorie, id, token);

        srvIdeen.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Toast.makeText(EditIdeeActivity.this, "Het idee is succesvol aangepast", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(EditIdeeActivity.this, NavigationActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
