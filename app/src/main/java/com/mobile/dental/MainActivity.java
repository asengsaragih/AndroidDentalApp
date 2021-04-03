package com.mobile.dental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //configure drawer menu
        setupDrawerMenu();
    }

    private void setupDrawerMenu() {
        //initialize component from xml
        DrawerLayout drawer = findViewById(R.id.drawer_main);
        NavigationView navigationView = findViewById(R.id.nav_main);

        //toogle for arrow
        mToggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(mToggle);
        mToggle.syncState();

        //show burger menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //drawer menu clicked
        navigationView.setNavigationItemSelectedListener(item -> {
            selectItemDrawer(item, drawer);
            return true;
        });
    }

    private void selectItemDrawer(MenuItem menuItem, DrawerLayout drawerLayout) {
        //item clicked in drawer menu
        //kenapa pkai post delay, biar ngga ngelag ketika item di klik
        switch (menuItem.getItemId()) {
            case R.id.action_home:
                drawerLayout.closeDrawers();
                break;
            case R.id.action_daftar:
                drawerLayout.closeDrawers();
                new Handler().postDelayed(() -> startActivity(new Intent(this, RegisterActivity.class)), 250);
                break;
            case R.id.action_history:
                drawerLayout.closeDrawers();
                break;
            case R.id.action_profile:
                drawerLayout.closeDrawers();
                break;
        }
    }
}