package com.mobile.dental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActionBarDrawerToggle mToggle;
    private Button mComplaintsDiseaseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initial component
        init();

        //set data
        setData();

        //configure drawer menu
        setupDrawerMenu();
    }

    private void setData() {
        //action dari komponent
        mComplaintsDiseaseButton.setOnClickListener(this);
    }

    private void init() {
        //initial component in layout
        mComplaintsDiseaseButton = findViewById(R.id.button_main_disease_complaints);
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
                new Handler().postDelayed(() -> { startActivity(new Intent(this, HistoryActivity.class)); }, 250);
                break;
            case R.id.action_profile:
                drawerLayout.closeDrawers();
                new Handler().postDelayed(() -> { startActivity(new Intent(this, ProfileActivity.class)); }, 250);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_main_disease_complaints:
                startActivity(new Intent(getApplicationContext(), ChatBotActivity.class));
                break;
        }
    }
}