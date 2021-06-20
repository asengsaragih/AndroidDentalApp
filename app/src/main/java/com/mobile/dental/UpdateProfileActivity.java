package com.mobile.dental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobile.dental.base.BaseActivity;
import com.mobile.dental.base.Constant;
import com.mobile.dental.model.Profile;
import com.mobile.dental.model.UpdateProfileResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends BaseActivity implements View.OnClickListener {

    private EditText mFullnameEdittext;
    private EditText mUsernameEdittext;
    private EditText mPasswordEdittext;
    private EditText mEmailEdittext;
    private EditText mContactEdittext;

    private Button mUpdateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        init();
        setData();
    }

    private void init() {
        mFullnameEdittext = findViewById(R.id.edittext_update_profile_fullname);
        mUsernameEdittext = findViewById(R.id.edittext_update_profile_username);
        mPasswordEdittext = findViewById(R.id.edittext_update_profile_password);
        mEmailEdittext = findViewById(R.id.edittext_update_profile_email);
        mContactEdittext = findViewById(R.id.edittext_update_profile_contact);

        mUpdateButton = findViewById(R.id.button_update_profile_save);
    }

    private void setData() {
        mUpdateButton.setOnClickListener(this);

        //set data untuk edittext
        Profile profile = getProfileData();

        mFullnameEdittext.setText(profile.getFullname());
        mUsernameEdittext.setText(profile.getUsername());
        mPasswordEdittext.setText(profile.getPassword());
        mEmailEdittext.setText(profile.getEmail());
        mContactEdittext.setText(profile.getKontak());
    }

    private Profile getProfileData() {
        //mengambil data intent dari profile activity
        return (Profile) getIntent().getSerializableExtra(Constant.INTENT_UPDATE_PROFILE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_update_profile_save:
                updateProfile();
                break;
        }
    }

    private void updateProfile() {
        //fungsi untuk update profile
        if (isEdittextEmpty(mFullnameEdittext)
                || isEdittextEmpty(mUsernameEdittext)
                || isEdittextEmpty(mPasswordEdittext)
                || isEdittextEmpty(mEmailEdittext)
                || isEdittextEmpty(mContactEdittext)) {
            toast("Field tidak boleh kosong");
            return;
        }

        String uid = getProfileData().getIdUser();
        String fullname = mFullnameEdittext.getText().toString();
        String username = mUsernameEdittext.getText().toString();
        String password = mPasswordEdittext.getText().toString();
        String email = mEmailEdittext.getText().toString();
        String contact = mContactEdittext.getText().toString();

        showLoading(true);

        Call<UpdateProfileResult> updateCall = mApiService.updateProfile(uid, fullname, username, password, email, contact);
        updateCall.enqueue(new Callback<UpdateProfileResult>() {
            @Override
            public void onResponse(Call<UpdateProfileResult> call, Response<UpdateProfileResult> response) {
                showLoading(false);

                if (response.code() == 200 && response.body() != null) {
                    toast(response.body().getMessage());

                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                } else {
                    toast("error update data");

                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResult> call, Throwable t) {
                showLoading(false);
            }
        });
    }
}