package com.example.iatiimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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
        Log.d("TAG_2", "Test");

        Idee[] idees = new Idee[5];
        idees[0] = new Idee("Twitch plays Pokémon","Twitch plays Pokémon bot maken voor Platinum","Hobby");
        idees[1] = new Idee("Laravel Sanctum gebruiken","Laravel Sanctum opzetten voor IKSE","School");
        idees[2] = new Idee("Hackthebox.eu proberen","Hackthebox.eu is een legal hacking site","Hobby");
        idees[3] = new Idee("IPMEDT5 kaart draaien","Voor IOT moeten er nog een input knop komen en dan via een kaart draaien","School");
        idees[4] = new Idee("EK schema maken","Een schema voor het EK maken ps moet de wedstrijden nog op zoeken","Hobby");

        Log.d("TAG_3", "Test");
        recyclerViewAdapter = new IdeeAdapter(idees);
        recyclerView.setAdapter(recyclerViewAdapter);
        Log.d("TAG_4", "Test");
    }
}