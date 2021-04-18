package com.mobile.dental;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.dental.adapter.HistoryAdapter;
import com.mobile.dental.model.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView mHistoryRecycleview;
    private View mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //inisialisasi component yang ada di dalam layout
        init();

        //untuk set data atau pemanggilan data
        setData();
    }

    private void setData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mHistoryRecycleview.setLayoutManager(layoutManager);

        //initisialisasi list
        List<History> histories = new ArrayList<>();

        //pengisian list item
        histories.add( new History("14 Jan 2020 | 15.30 WIB","Halo Saya Ini Text"));
        histories.add( new History("15 Jan 2020 | 15.30 WIB","Halo Saya Ini Text 2"));
        histories.add( new History("16 Jan 2020 | 15.30 WIB","Halo Saya Ini Text 3"));
        histories.add( new History("24 Jan 2020 | 15.30 WIB","Halo Saya Ini Text 4"));
        histories.add( new History("26 Jan 2020 | 15.30 WIB", getString(R.string.lorem_ipsum)));

        HistoryAdapter adapter = new HistoryAdapter(this, histories, mEmptyView, new HistoryAdapter.ClickHandler() {
            @Override
            public void onItemClicked(History history) {
                //dipanggil metode detail popup nya
                detailPopUp(history);
            }
        });

        mHistoryRecycleview.setAdapter(adapter);
        adapter.updateEmptyView();
    }

    private void detailPopUp(History history) {
        //fungsi untuk menampilkan detail dalam bentuk popup

        //init component yang dibutuhkan untuk membuat popup
        //pakai import -> "import androidx.appcompat.app.AlertDialog;"
        AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_history, null);

        //set view nya
        builder.setView(view);

        //inisialisasi component yang berasal dari dialog box
        TextView date = view.findViewById(R.id.textView_dialog_history_date);
        TextView content = view.findViewById(R.id.textView_dialog_history_content);

        //set data dari list itemnya
        date.setText(history.getDate());
        content.setText(history.getContent());

        //show dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void init() {
        mHistoryRecycleview = findViewById(R.id.recycle_history);
        mEmptyView = findViewById(R.id.emptyview_history);
    }
}