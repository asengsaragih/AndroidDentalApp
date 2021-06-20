package com.mobile.dental;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.mobile.dental.base.BaseActivity;
import com.mobile.dental.base.Constant;
import com.mobile.dental.model.History;

public class DetailHistoryActivity extends BaseActivity {


    private TextView mIdPendaftaranTextView;
    private TextView mPasienTextView;
    private TextView mDokterTextView;
    private TextView mTanggalTextView;
    private TextView mWaktuTextView;
    private TextView mHasilTextView;
    private TextView mSaranTextView;
    private TextView mResepTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);

        init();
        setData();
    }
    private void init(){
        mIdPendaftaranTextView = findViewById(R.id.textview_detail_history_id_pendaftaran);
        mPasienTextView = findViewById(R.id.textview_detail_history_pasient);
        mDokterTextView= findViewById(R.id.textview_detail_history_dokter);
        mTanggalTextView = findViewById(R.id.textview_detail_history_tanggal_pelayanan);
        mWaktuTextView = findViewById(R.id.textview_detail_history_waktu_pelayanan);
        mHasilTextView = findViewById(R.id.textview_detail_history_hasil_pemeriksaan);
        mSaranTextView = findViewById(R.id.textview_detail_history_saran);
        mResepTextView = findViewById(R.id.textview_detail_history_resep);

    }
    private void setData(){

        mIdPendaftaranTextView.setText(getHistoryData().getId());
        mPasienTextView.setText(getHistoryData().getName());
        mDokterTextView.setText(getHistoryData().getDokter());
        mTanggalTextView.setText(getHistoryData().getTanggal());
        mWaktuTextView.setText(getHistoryData().getWaktu());
        mHasilTextView.setText(getHistoryData().getHasil());
        mSaranTextView.setText(getHistoryData().getSaran());
        mResepTextView.setText(getHistoryData().getResep());

    }
    private History getHistoryData(){
        //get data from history
        return (History) getIntent().getSerializableExtra(Constant.INTENT_DETAIL_HISTPRY);
    }
}