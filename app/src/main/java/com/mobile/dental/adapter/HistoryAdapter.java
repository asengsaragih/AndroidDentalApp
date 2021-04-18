package com.mobile.dental.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.dental.R;
import com.mobile.dental.model.History;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {

    final Context mContext;
    final List<History> mData;
    final View mEmptyView;
    final ClickHandler mHandler;

    public HistoryAdapter(Context mContext, List<History> mData, View mEmptyView, ClickHandler mHandler) {
        this.mContext = mContext;
        this.mData = mData;
        this.mEmptyView = mEmptyView;
        this.mHandler = mHandler;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_history, parent, false);
        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        History history = mData.get(position);

        holder.date.setText(history.getDate());
        holder.content.setText(history.getContent());

        holder.itemView.setOnClickListener(v -> {
            mHandler.onItemClicked(history);
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void updateEmptyView(){
        //fungsi untuk mengatur list jika data kosong
        if (mData.size() == 0)
            mEmptyView.setVisibility(View.VISIBLE);
        else
            mEmptyView.setVisibility(View.GONE);
    }

    static class HistoryHolder extends RecyclerView.ViewHolder {

        final TextView date;
        final TextView content;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.textView_list_item_history_date);
            content = itemView.findViewById(R.id.textView_list_item_history_content);
        }
    }

    public interface ClickHandler {
        void onItemClicked(History history);
    }
}