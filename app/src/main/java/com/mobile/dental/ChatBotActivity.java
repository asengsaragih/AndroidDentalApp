package com.mobile.dental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mobile.dental.adapter.ChatBotAdapter;
import com.mobile.dental.base.Constant;
import com.mobile.dental.model.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatBotActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mMessageEdittext;
    private Button mSendButton;
    private RecyclerView mChatRecycle;
    private ChatBotAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);

        init();

        setData();

        dummyBot();
    }

    private void dummyBot() {
        //dummy long clicked
        mSendButton.setOnLongClickListener(v -> {
            Chat chat = new Chat(
                    "Terimakasih telah menghubungi kami",
                    "18.02",
                    Constant.CHAT_TYPE_LEFT
            );

            mAdapter.updateChat(chat);

            return false;
        });
    }

    private void setData() {
        mSendButton.setOnClickListener(this);

        //recycle setup
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        mChatRecycle.setLayoutManager(layoutManager);

        List<Chat> chats = new ArrayList<>();
        chats.add(new Chat(
                "Hai, Ada yang bisa saya bantu?",
                "18.00",
                Constant.CHAT_TYPE_LEFT
        ));

        mAdapter = new ChatBotAdapter(this, chats);
        mChatRecycle.setAdapter(mAdapter);
    }

    private void init() {
        //init component
        mMessageEdittext = findViewById(R.id.edittext_chat_bot_message);
        mSendButton = findViewById(R.id.button_chat_bot_send);
        mChatRecycle = findViewById(R.id.recycle_chat_bot);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_chat_bot_send:
                if (isEdittextEmpty(mMessageEdittext))
                    return;
                else
                    send();
                break;
        }
    }

    private void send() {
        Chat chat = new Chat(
                mMessageEdittext.getText().toString(),
                "18.01",
                Constant.CHAT_TYPE_RIGHT
        );

        mAdapter.updateChat(chat);
        //clear message
        mMessageEdittext.setText("");
    }

    private boolean isEdittextEmpty(EditText editText) {
        return TextUtils.isEmpty(editText.getText().toString());
    }
}