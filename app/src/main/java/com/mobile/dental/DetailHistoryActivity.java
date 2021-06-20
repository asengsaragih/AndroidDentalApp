package com.mobile.dental;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.mobile.dental.base.BaseActivity;
import com.mobile.dental.base.Constant;
import com.mobile.dental.model.History;

public class DetailHistoryActivity extends BaseActivity {

    private TextView mIdPendaftaranTextview;
    private TextView mPasienTextview;
    private TextView mDokterTextview;
    private TextView mTanggalTextview;
    private TextView mWaktuTextview;
    private TextView mHasilTextview;
    private TextView mSaranTextview;
    private TextView mResepTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);

        init();
        setData();
    }

    private void init() {
        mIdPendaftaranTextview = findViewById(R.id.textview_detail_history_id_pendaftaran);
        mPasienTextview = findViewById(R.id.textview_detail_history_pasien);
        mDokterTextview = findViewById(R.id.textview_detail_history_dokter);
        mTanggalTextview = findViewById(R.id.textview_detail_history_tanggal);
        mWaktuTextview = findViewById(R.id.textview_detail_history_waktu);
        mHasilTextview = findViewById(R.id.textview_detail_history_hasil);
        mSaranTextview = findViewById(R.id.textview_detail_history_saran);
        mResepTextview = findViewById(R.id.textview_detail_history_resep);
    }

    private void setData() {
        mIdPendaftaranTextview.setText(getHistoryData().getId());
        mPasienTextview.setText(getHistoryData().getName());
        mDokterTextview.setText(getHistoryData().getDokter());
        mTanggalTextview.setText(getHistoryData().getTanggal());
        mWaktuTextview.setText(getHistoryData().getWaktu());
        mHasilTextview.setText(getHistoryData().getHasil());
        mSaranTextview.setText(getHistoryData().getSaran());
        mResepTextview.setText(getHistoryData().getResep());
    }

    private History getHistoryData() {
        //get data from history
        return (History) getIntent().getSerializableExtra(Constant.INTENT_DETAIL_HISTORY);
    }
}