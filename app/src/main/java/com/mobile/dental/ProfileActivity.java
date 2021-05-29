package com.mobile.dental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.mobile.dental.base.BaseActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends BaseActivity implements View.OnClickListener {

    private CircleImageView mPictureImageView;

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
        Glide.with(getApplicationContext()).load("https://cdn.psychologytoday.com/sites/default/files/styles/article-inline-half-caption/public/field_blog_entry_images/2018-09/shutterstock_648907024.jpg?itok=0hb44OrI").into(mPictureImageView);

    }

    private void init(){
        mPictureImageView = findViewById(R.id.imageview_profile_picture);

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
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
            case R.id.action_edit:
                startActivity(new Intent(getApplicationContext(), UpdateProfileActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

    }
}