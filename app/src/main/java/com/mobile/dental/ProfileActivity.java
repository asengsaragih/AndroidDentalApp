package com.mobile.dental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSignOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //init component
        init();

        //set data component
        setData();
    }

    private void setData() {
        mSignOutButton.setOnClickListener(this);
    }

    private void init() {
        mSignOutButton = findViewById(R.id.button_profile);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_profile:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
        }
    }
}