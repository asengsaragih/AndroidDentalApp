package com.mobile.dental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private View loginToRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        setData();
    }

    private void init() {
        loginToRegister = findViewById(R.id.view_login_to_register);
    }

    private void setData() {
        loginToRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view_login_to_register:
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                break;
        }
    }
}