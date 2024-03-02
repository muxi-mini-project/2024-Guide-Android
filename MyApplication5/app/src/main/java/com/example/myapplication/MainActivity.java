package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import ui.DashboardFragment;
import ui.HomeFragment;
import ui.NotificationsFragment;
import ui.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    public static Intent newIntent(Context packgeContext, String mainActivity) {
        Intent intent = new Intent(packgeContext,MainActivity.class);
        //intent.putExtra(User_Identity,userIdentity);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // 设置默认显示的 Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new HomeFragment())
                .commit();
    }

    @SuppressLint("NonConstantResourceId")
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();
                int id = item.getItemId();
                if (id == R.id.navigation_home) {
                    selectedFragment = new HomeFragment();
                } else if (id == R.id.navigation_achieve) {
                    selectedFragment = new DashboardFragment();
                } else if (id == R.id.navigation_mission) {
                    selectedFragment = new NotificationsFragment();
                } else if (id == R.id.navigation_my_task) {
                    selectedFragment = new ProfileFragment();
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, selectedFragment)
                        .commit();

                return true;
            };
}
