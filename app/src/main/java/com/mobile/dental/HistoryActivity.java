package com.mobile.dental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.mobile.dental.adapter.HistoryAdapter;
import com.mobile.dental.model.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView mHistoryRecycleView;
    private View mEmptyView;

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

        //pengisian list item
        histories.add(new History("14 jan 2020 | 15.30 WIB", "Halo saya ini text"));
        histories.add(new History("13 jan 2020 | 15.30 WIB", "Halo saya ini text"));
        histories.add(new History("14 jan 2020 | 15.30 WIB", "Halo saya ini text"));
        histories.add(new History("16 jan 2020 | 15.30 WIB", "Halo saya ini text"));
        histories.add(new History("17 jan 2020 | 15.30 WIB", "Halo saya ini text"));
        histories.add(new History("18 jan 2020 | 15.30 WIB", "Halo saya ini text"));

        HistoryAdapter adapter = new HistoryAdapter(this, histories, mEmptyView, new HistoryAdapter.ClickHandler() {
            @Override
            public void onItemClicked(History history) {

            }
        });
    }

    private void init() {
        mHistoryRecycleView = findViewById(R.id.recycle_history);
        mEmptyView = findViewById(R.id.emptyview_history);
    }
}