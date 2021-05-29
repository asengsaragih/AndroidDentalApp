package com.mobile.dental.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.mobile.dental.R;
import com.mobile.dental.model.Auth;

public class Session {
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private Context mContext;
    private Gson gson = new Gson();

    public static final String SESSION_USER = "session_user";

    public Session(Context mContext) {
        this.mContext = mContext;
        mPreferences = mContext.getSharedPreferences(
                mContext.getString(R.string.app_name),
                Context.MODE_PRIVATE
        );
        mEditor = mPreferences.edit();
    }

    public void setAuthSession(Auth auth) {
        //set auth setelah login
        String authString = gson.toJson(auth);

        //put in sharedpref
        mEditor.putString(SESSION_USER, authString);
        mEditor.commit();
    }

    public Auth getAuthSession() {
        //get auth session
        Auth auth = new Auth(null, null, null, null, null);
        String result = mPreferences.getString(
                SESSION_USER,
                gson.toJson(auth)
        );

        return gson.fromJson(result, Auth.class);
    }
}
