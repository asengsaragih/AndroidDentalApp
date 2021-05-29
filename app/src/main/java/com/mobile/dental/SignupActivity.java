package com.mobile.dental;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobile.dental.base.BaseActivity;
import com.mobile.dental.model.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends BaseActivity implements View.OnClickListener {
    private View resgiterToLogin;
    private Button mRegisterButton;

    private EditText mUsernameEdittext;
    private EditText mEmailEdittext;
    private EditText mPasswordEdittext;
    private EditText mPhoneEdittext;

    private static final String TAG = "SignupActivityTag";

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

        mUsernameEdittext = findViewById(R.id.edittext_register_username);
        mEmailEdittext = findViewById(R.id.edittext_register_email);
        mPasswordEdittext = findViewById(R.id.edittext_register_password);
        mPhoneEdittext = findViewById(R.id.edittext_register_phone);
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
                register();
                break;
        }
    }

    private void register() {
        //validasik isi dari edittext
        if (isEdittextEmpty(mUsernameEdittext) ||
                isEdittextEmpty(mEmailEdittext) ||
                isEdittextEmpty(mPasswordEdittext) ||
                isEdittextEmpty(mPhoneEdittext)) {

            toast("Field tidak boleh kosong");
            return;
        }

//        showLoading(true);

        String username = mUsernameEdittext.getText().toString();
        String email = mEmailEdittext.getText().toString();
        String password = mPasswordEdittext.getText().toString();
        String phone = mPhoneEdittext.getText().toString();

        Call<Register> registerCall = mApiService.register(username, password, email, phone);

        //execute api nya
        registerCall.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if (response.code() != 200 || response.body() == null) {
                    showLoading(false);
                    toast("Registrasi Gagal " + response.code());
                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                }

                //validate registrasi data
                Register register = response.body();

                showLoading(false);

                if (register.getStatus().equals("Failed")) {
                    toast(register.getMessage());
                } else {
                    toast("Pendaftaran Berhasil");

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                showLoading(false);
                toast("Registrasi Gagal " + t.getMessage());
                Log.d(TAG, "onResponse: " + t.getMessage());
            }
        });
    }
}