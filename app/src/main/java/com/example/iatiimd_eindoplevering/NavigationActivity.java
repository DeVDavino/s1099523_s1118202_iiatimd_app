package com.example.iatiimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class NavigationActivity extends AppCompatActivity {

    //    Initialize variable
    MeowBottomNavigation bottomNavigation;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable
        bottomNavigation = findViewById(R.id.bottom_navigation);

        // Add menu item
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home_black_24dp));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_add_circle_outline_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_person_24));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                //Initialize fragment
                Fragment fragment = null;
                //Check condition
                switch(item.getId()){
                    case 1:
                        //When id is 1
                        //Initialize home fragment
                        fragment = new HomeFragment();
                        break;

                    case 2:
                        //When id is 2
                        //Initialize idea fragment
                        fragment = new IdeeFragment();
                        break;

                    case 3:
                        //When id is 3
                        //Initialize profile fragment
                        fragment = new ProfileFragment();
                        break;


                }
                //load fragment
                loadFragment(fragment);



            }
        });

        //Set notification count
        bottomNavigation.setCount(1, "10");
        //Set home fragment initally selected
        bottomNavigation.show(2, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Display toast
                Toast.makeText(getApplicationContext()
                        ,"You Clicked" + item.getId()
                        ,Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //Display toast
                Toast.makeText(getApplicationContext()
                        ,"You Reselected" + item.getId()
                        ,Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void loadFragment(Fragment fragment){
        //Replace fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
    }


}