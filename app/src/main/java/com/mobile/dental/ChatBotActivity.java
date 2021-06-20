package com.mobile.dental;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.dental.adapter.ChatBotAdapter;
import com.mobile.dental.base.BaseActivity;
import com.mobile.dental.base.Constant;
import com.mobile.dental.model.Bot;
import com.mobile.dental.model.Chat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatBotActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "ChatBotActivityTag";

    private EditText mMessageEdittext;
    private Button mSendButton;
    private RecyclerView mChatRecycle;
    private ChatBotAdapter mAdapter;

    private Boolean isChoose = false;
    private String numberPickedChoose = null;

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

        mAdapter = new ChatBotAdapter(this, chats);
        mChatRecycle.setAdapter(mAdapter);

        //inisial awal bot
        showLoading(true);
        Call<Bot> botCall = mApiService.getInitialBot();
        botCall.enqueue(new Callback<Bot>() {
            @Override
            public void onResponse(Call<Bot> call, Response<Bot> response) {
                showLoading(false);

                if (response.body() == null || response.code() != 200) {
                    return;
                }

                Bot bot = response.body();
                Chat chat = new Chat(bot.getFirstChat(), getCurrentTime(), Constant.CHAT_TYPE_LEFT);
                mAdapter.updateChat(chat);
            }

            @Override
            public void onFailure(Call<Bot> call, Throwable t) {
                showLoading(false);
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void init() {
        //init komponen
        mMessageEdittext = findViewById(R.id.edittext_chat_bot_message);
        mSendButton = findViewById(R.id.button_chat_bot_send);
        mChatRecycle = findViewById(R.id.recycle_chat_bot);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_chat_bot_send:
                if (isEditTextEmpty(mMessageEdittext)) {
                    return;
                } else {
                    if (!isChoose) {
                        send();
                    } else {
                        sendResponse(numberPickedChoose);
                    }
                }
                break;
        }
    }

    private void sendResponse(String numberChoose) {
        String requestString = mMessageEdittext.getText().toString();

        if (!isNumeric(requestString)) {
            toast("Pemilihan harus menggunakan angka");
            return;
        }

        Chat chat = new Chat(
                requestString,
                getCurrentTime(),
                Constant.CHAT_TYPE_RIGHT
        );
        mAdapter.updateChat(chat);

        //autoscroll
        mChatRecycle.smoothScrollToPosition(mAdapter.getLastPosition());
        //clear message
        mMessageEdittext.setText("");

        Call<List<Bot.Chatbot>> resultDeskripsiCall = mApiService.resultDeskripsi(numberChoose, requestString);
        resultDeskripsiCall.enqueue(new Callback<List<Bot.Chatbot>>() {
            @Override
            public void onResponse(Call<List<Bot.Chatbot>> call, Response<List<Bot.Chatbot>> response) {
                if (response.body() == null || response.code() != 200) {
                    return;
                }

                List<Bot.Chatbot> chatbots = response.body();

                if (chatbots.size() == 0) {
                    return;
                }

                Bot.Chatbot chatbot = chatbots.get(0);

                Chat chatResult = new Chat(
                        chatbot.getDeskripsi(),
                        getCurrentTime(),
                        Constant.CHAT_TYPE_LEFT
                );

                mAdapter.updateChat(chatResult);

                mChatRecycle.smoothScrollToPosition(mAdapter.getLastPosition());
            }

            @Override
            public void onFailure(Call<List<Bot.Chatbot>> call, Throwable t) {

            }
        });
    }

    private void send() {
        String chooseNumberString = mMessageEdittext.getText().toString();

        if (!isNumeric(chooseNumberString)) {
            toast("Pemilihan harus menggunakan angka");
            return;
        }

        Chat chat = new Chat(
                chooseNumberString,
                getCurrentTime(),
                Constant.CHAT_TYPE_RIGHT
        );
        mAdapter.updateChat(chat);

        mChatRecycle.smoothScrollToPosition(mAdapter.getLastPosition());
        //clear message
        mMessageEdittext.setText("");

        Call<List<Bot.Chatbot>> chooseLayanan = mApiService.chooseLayanan(chooseNumberString);
        chooseLayanan.enqueue(new Callback<List<Bot.Chatbot>>() {
            @Override
            public void onResponse(Call<List<Bot.Chatbot>> call, Response<List<Bot.Chatbot>> response) {

                if (response.body() == null || response.code() != 200) {
                    return;
                }

                List<Bot.Chatbot> chooseResponse = response.body();

                if (chooseResponse.size() == 0) {
                    return;
                }

                StringBuilder result = new StringBuilder("Pilih keluhan anda" + "\n\n");

                for (Bot.Chatbot chatbot : chooseResponse) {
                    result.append(chatbot.getContent()).append(". ");
                    result.append(chatbot.getKeyword()).append("\n");
                }

                //harus dikondisikan agar bisa mendapatkan response dari result ke tiga
                isChoose = true;
                numberPickedChoose = chooseNumberString;

                Chat chatResult = new Chat(
                        result.toString(),
                        getCurrentTime(),
                        Constant.CHAT_TYPE_LEFT
                );

                mAdapter.updateChat(chatResult);

                mChatRecycle.smoothScrollToPosition(mAdapter.getLastPosition());
            }

            @Override
            public void onFailure(Call<List<Bot.Chatbot>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isEditTextEmpty(EditText editText) {
        return TextUtils.isEmpty(editText.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bot_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                refresh();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        //buat ulang tampilan
        showLoading(true);
        mAdapter.clear();

        //mengembalikan nilai
        isChoose = false;
        numberPickedChoose = null;

        Call<Bot> botCall = mApiService.getInitialBot();
        botCall.enqueue(new Callback<Bot>() {
            @Override
            public void onResponse(Call<Bot> call, Response<Bot> response) {
                showLoading(false);

                if (response.body() == null || response.code() != 200) {
                    return;
                }

                Bot bot = response.body();
                Chat chat = new Chat(bot.getFirstChat(), getCurrentTime(), Constant.CHAT_TYPE_LEFT);
                mAdapter.updateChat(chat);
            }

            @Override
            public void onFailure(Call<Bot> call, Throwable t) {
                showLoading(false);
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
