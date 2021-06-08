package com.example.iatiimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button toIdeeScreen = findViewById(R.id.button_idee);
        toIdeeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIdee();
            }
        });
    }

    public void openIdee() {
        Bundle bundleIdee = new Bundle();
        Intent intent = new Intent(this, NavigationActivity.class);
        intent.putExtras(bundleIdee);
        startActivity(intent);
    }
}