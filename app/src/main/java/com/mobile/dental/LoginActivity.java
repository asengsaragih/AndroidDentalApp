package com.mobile.dental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.mobile.dental.base.BaseActivity;
import com.mobile.dental.model.Auth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private View loginToRegister;
    private Button mLoginButton;

    //component
    private EditText mUsernameEdittext;
    private EditText mPasswordEdittext;

    @Override
    protected void onStart() {
        super.onStart();

        //check session, kalau session masih ada langsung ngarahin / intent ke main
        if (mSession.getAuthSession() != null && mSession.getAuthSession().getEmail() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        setData();
    }

    private void init() {
        loginToRegister = findViewById(R.id.view_login_to_register);
        mLoginButton = findViewById(R.id.button_login);
        mUsernameEdittext = findViewById(R.id.edittext_login_username);
        mPasswordEdittext = findViewById(R.id.edittext_login_password);
    }

    private void setData() {
        loginToRegister.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view_login_to_register:
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                break;
            case R.id.button_login:
                login();
                break;
        }
    }

    private void login() {
        //fungsi untuk login
        if (isEdittextEmpty(mUsernameEdittext) && isEdittextEmpty(mPasswordEdittext)){
            toast("Field tidak boleh kosong");
            return;
        }
        //tampilan loading
        showLoading(true);

        //execute fungsi nya ke api
        String username = mUsernameEdittext.getText().toString();
        String password = mPasswordEdittext.getText().toString();

        Call<Auth> loginCall = mApiService.login(username, password);
        loginCall.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                if (response.code() != 200 || response.body() == null){
                    //login gagal permasalahan internet
                    showLoading(false);
                    toast("Login gagal");
                    return;
                }

                //validasi login data
                showLoading(false);
                Auth auth = response.body();

                //validate account
                if (auth.getEmail()==null){
                    //username atau password salah
                    toast(auth.getMessage());
                    return;
                }
                //save session auth data in sharedpreference
                mSession.setAuthSession(auth);

                //intent to main activity
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                showLoading(false);
                toast("login gagal");
            }
        });
    }
}