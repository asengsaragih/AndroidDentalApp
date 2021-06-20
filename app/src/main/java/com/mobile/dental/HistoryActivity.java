package com.mobile.dental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.dental.adapter.HistoryAdapter;
import com.mobile.dental.base.BaseActivity;
import com.mobile.dental.base.Constant;
import com.mobile.dental.model.History;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends BaseActivity {

    private static final String TAG = "HistoryActivityTag";
    private RecyclerView mHistoryRecycleView;
    private View mEmptyView;
    private HistoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //insialisasi componen yg ada di dalam layout

        init();

        //untuk set data atau pemanggilan data
        setData();
    }

    private void setData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mHistoryRecycleView.setLayoutManager(layoutManager);

        List<History> histories = new ArrayList<>();

        mAdapter = new HistoryAdapter(this, histories, mEmptyView, new HistoryAdapter.ClickHandler() {
            @Override
            public void onItemClicked(History history) {
                //dipanggil metode detail popup
                Intent intent = new Intent(getApplicationContext(), DetailHistoryActivity.class);
                intent.putExtra(Constant.INTENT_DETAIL_HISTORY, history);
                startActivity(intent);
            }
        });

        mHistoryRecycleView.setAdapter(mAdapter);

        showLoading(true);

        //fungsi untuk manggil data history dari api
        Call<List<History>> historiesCall = mApiService.getHistories(mSession.getAuthSession().getId());
        historiesCall.enqueue(new Callback<List<History>>() {
            @Override
            public void onResponse(Call<List<History>> call, Response<List<History>> response) {
                if (response.code() != 200 || response.body() == null) {
                    showLoading(false);
                    return;
                }

                showLoading(false);
                mAdapter.updateData(response.body());
            }

            @Override
            public void onFailure(Call<List<History>> call, Throwable t) {
                showLoading(false);
            }
        });
    }

    private void init() {
        mHistoryRecycleView = findViewById(R.id.recycle_history);
        mEmptyView = findViewById(R.id.emptyview_history);
    }
}