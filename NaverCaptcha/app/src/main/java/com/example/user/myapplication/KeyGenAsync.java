package com.example.user.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by User on 2016-11-14.
 */

public class KeyGenAsync extends AsyncTask<Void, Void, String> {

    MainActivity activity;
    private ProgressDialog dialog;

    public KeyGenAsync(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(activity, "",
                "잠시만 기다려 주세요 ...", true);
    }

    @Override
    protected String doInBackground(Void... params) {
        OkHttpClient client = new OkHttpClient();
        String key = null;
        Request request = new Request.Builder()
                .url(MyConstant.NAVER_KEY_URL + "code=0")
                .get()
                .addHeader("x-naver-client-id", MyConstant.X_Naver_Client_Id)
                .addHeader("x-naver-client-secret", MyConstant.X_Naver_Client_Secret)
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            key = new JSONObject(response.body().string()).getString("key");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return key;
    }

    @Override
    protected void onPostExecute(String key) {
        super.onPostExecute(key);

        dialog.dismiss();
        Log.e("key", MyConstant.NAVER_CAPTCHA_URL + "key=" + key);

        Glide.with(activity).load(MyConstant.NAVER_CAPTCHA_URL + "key=" + key)
                .into(activity.captchaImage);

        MyConstant.NAVER_CHAPTCHA_KEY = key;
    }
}

