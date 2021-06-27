package com.example.iatiimd_eindoplevering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button toIdeeScreen = findViewById(R.id.button_idee);
        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_container, new HomeFragment()).commit();
//        toIdeeScreen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openIdee();
//            }
//        });
    }

    public BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch(item.getItemId()){
                        case R.id.navigation_home:
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.navigation_idee:
                            selectedFragment = new DashboardFragment();
                            break;

                        case R.id.navigation_profile:
                            selectedFragment = new NotificationFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_container,
                            selectedFragment).commit();

                    return true;

                }
    };

    public void openIdee() {
        Bundle bundleIdee = new Bundle();
        Intent intent = new Intent(this, NavigationActivity.class);
        intent.putExtras(bundleIdee);
        startActivity(intent);
    }
}