package com.mobile.dental.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.dental.R;
import com.mobile.dental.base.Constant;
import com.mobile.dental.model.Chat;

import java.util.List;

public class ChatBotAdapter extends RecyclerView.Adapter<ChatBotAdapter.ChatBotHolder> {

    private final Context mContext;
    private List<Chat> mData;

    public ChatBotAdapter(Context mContext, List<Chat> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ChatBotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_chat, parent, false);
        return new ChatBotHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatBotHolder holder, int position) {
        Chat chat = mData.get(position);

        if (chat.getType() == Constant.CHAT_TYPE_LEFT) {
            //disable box
            holder.leftBox.setVisibility(View.VISIBLE);
            holder.rightBox.setVisibility(View.GONE);

            //text
            holder.chatLeft.setText(chat.getText());
            holder.timeLeft.setText(chat.getTime());
        } else {
            //disable box
            holder.rightBox.setVisibility(View.VISIBLE);
            holder.leftBox.setVisibility(View.GONE);

            //text
            holder.chatRight.setText(chat.getText());
            holder.timeRight.setText(chat.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void updateChat(Chat chat) {
        this.mData.add(chat);
        notifyDataSetChanged();
    }

    static class ChatBotHolder extends RecyclerView.ViewHolder {

        final TextView chatLeft;
        final TextView timeLeft;
        final TextView chatRight;
        final TextView timeRight;

        final View leftBox;
        final View rightBox;

        public ChatBotHolder(@NonNull View itemView) {
            super(itemView);

            chatLeft = itemView.findViewById(R.id.text_gchat_message_other);
            timeLeft = itemView.findViewById(R.id.text_gchat_timestamp_other);
            chatRight = itemView.findViewById(R.id.text_gchat_message_me);
            timeRight = itemView.findViewById(R.id.text_gchat_timestamp_me);

            leftBox = itemView.findViewById(R.id.view_chat_left);
            rightBox = itemView.findViewById(R.id.view_chat_right);
        }
    }
}