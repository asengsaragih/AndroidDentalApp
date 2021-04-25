package com.mobile.dental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private View resgiterToLogin;
    private Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        setData();
    }

    private void setData() {
        resgiterToLogin.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);
    }

    private void init() {
        resgiterToLogin = findViewById(R.id.view_register_to_login);
        mRegisterButton = findViewById(R.id.button_register);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_register_to_login:
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.button_register:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
        }
    }
}