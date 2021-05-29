package com.mobile.dental.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.mobile.dental.R;
import com.mobile.dental.api.ApiClient;
import com.mobile.dental.api.ApiService;

public class BaseActivity extends AppCompatActivity {

    protected AppCompatActivity mActivity;
    protected ApiService mApiService;

    //untuk loading
    private AlertDialog mLoadingDialog;

    //untuk session
    protected Session mSession;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;

        //disini ngoding seluruh fungsi yang dipanggil berulang ulang
        //contoh kaya toast dan lainnya
        //pemanggilan, tinggal ganti extend actvity nya ke baseActivity
        //contohnya ada di main activity

        //intialize session
        mSession = new Session(this);

        //request api
        mApiService = ApiClient.builder().create(ApiService.class);

        //inisialisasi loading bar
        loadingInitialize();
    }

    private void loadingInitialize() {
        //function for loading bar in dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.progress_loading, null);
        builder.setView(view);
        builder.setCancelable(false);
        mLoadingDialog = builder.create();
    }

    protected void showLoading(boolean show) {
        //fungsi untuk menampilkan loading diseluruh activity yang terdaftar
        if (show)
            mLoadingDialog.show();
        else
            mLoadingDialog.dismiss();
    }

    protected boolean isEdittextEmpty(EditText editText) {
        //fungsi untuk check value dari edittext
        return TextUtils.isEmpty(editText.getText().toString());
    }

    protected void toast(String message) {
        //fungsi untuk menampilkan toast secara simple
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
