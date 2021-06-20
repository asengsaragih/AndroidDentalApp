package com.mobile.dental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobile.dental.base.BaseActivity;
import com.mobile.dental.base.Constant;
import com.mobile.dental.model.Auth;
import com.mobile.dental.model.Profile;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends BaseActivity implements View.OnClickListener {

    private Profile PROFILE_DATA = null;

    private TextView mUIDTextview;
    private TextView mFullnameTextview;
    private TextView mUsernameTextview;
    private TextView mEmailTextview;
    private TextView mKontakTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //init component
        init();

        //set data component
        setData();
    }

    private void init() {
        //init component dari layout

        mUIDTextview = findViewById(R.id.textview_profile_uid);
        mFullnameTextview = findViewById(R.id.textview_profile_fulname);
        mUsernameTextview = findViewById(R.id.textview_profile_username);
        mEmailTextview = findViewById(R.id.textview_profile_email);
        mKontakTextview = findViewById(R.id.textview_profile_contact);
    }

    private void setData() {
        //get profile dari api
        getProfile();
    }

    private void getProfile() {
        //get profile from api
        showLoading(true);

        Call<List<Profile>> getProfileCall = mApiService.getUser(mSession.getAuthSession().getId());
        getProfileCall.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                if (response.code() != 200 || response.body() == null || response.body().size() == 0) {
                    showLoading(false);
                    toast("Gagal memuat data");
                } else {
                    showLoading(false);
                    Profile profile = response.body().get(0);

                    //masukkan kedalam tmp profile untuk edit data
                    PROFILE_DATA = profile;

                    //set data nya
                    mUIDTextview.setText(profile.getIdUser());
                    mFullnameTextview.setText(profile.getFullname());
                    mUsernameTextview.setText(profile.getUsername());
                    mEmailTextview.setText(profile.getEmail());
                    mKontakTextview.setText(profile.getKontak());
                }
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                showLoading(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sign_out:
                logout();
                break;
            case R.id.action_edit:
                updateProfile();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateProfile() {
        //fungsi update profile
        if (PROFILE_DATA == null) {
            getProfile();
        } else {
            //pindah ke update profile activity + kirim data profile data yang diambil dari api
            Intent intent = new Intent(getApplicationContext(), UpdateProfileActivity.class);
            intent.putExtra(Constant.INTENT_UPDATE_PROFILE, PROFILE_DATA);
            startActivity(intent);
        }
    }

    private void logout() {
        //fungsi untuk logout
        //clear session nya
        Auth auth = new Auth(null, null, null, null, null, null);
        mSession.setAuthSession(auth);

        //arahkan ke login activity dan di tambahkan flag agar tidak bisa balik ke profile activity
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

    }
}