package com.mobile.dental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private CircleImageView mPictureImageview;

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
        Glide.with(getApplicationContext())
                .load("https://cdn.lifehack.org/wp-content/uploads/2014/03/shutterstock_97566446.jpg")
                .into(mPictureImageview);
    }

    private void init() {
        //initial component
        mPictureImageview = findViewById(R.id.imageview_profile_picture);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sign_out:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
            case R.id.action_edit:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

    }
}