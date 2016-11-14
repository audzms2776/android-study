package com.example.user.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by User on 2016-11-14.
 */

public class CheckInputAsync extends AsyncTask<Void, Void, ResultEntity> {

    private String inputTxt;
    private MainActivity activity;
    private ProgressDialog dialog;

    public CheckInputAsync(Context activity, String inputTxt) {
        this.inputTxt = inputTxt;
        this.activity = (MainActivity) activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(activity, "",
                "잠시만 기다려 주세요 ...", true);
    }

    @Override
    protected ResultEntity doInBackground(Void... params) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(MyConstant.NAVER_KEY_URL + "code=1&value=" + inputTxt + "&key=" + MyConstant.NAVER_CHAPTCHA_KEY)
                .get()
                .addHeader("x-naver-client-id", MyConstant.X_Naver_Client_Id)
                .addHeader("x-naver-client-secret", MyConstant.X_Naver_Client_Secret)
                .build();

        Response response;
        ResultEntity resultEntity = null;
        try {
            response = client.newCall(request).execute();
            resultEntity = parseString(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultEntity;
    }

    @Override
    protected void onPostExecute(ResultEntity resultEntity) {
        super.onPostExecute(resultEntity);

        dialog.dismiss();

        if (resultEntity == null) {
            return;
        } else if (resultEntity.isResult()) {
            activity.resultTxt.setText("결과 : " + resultEntity.isResult() + "\n걸린 시간 : "
                    + resultEntity.getResponseTime());
        } else {
            new KeyGenAsync(activity).execute();
            Toast.makeText(activity, "실패~~", Toast.LENGTH_SHORT).show();
        }
    }

    private ResultEntity parseString(String response) throws JSONException {

        JSONObject jsonObject = new JSONObject(response);
        boolean tempResult = jsonObject.getBoolean("result");
        int tempResponseTime = jsonObject.getInt("responseTime");

        return new ResultEntity(tempResult, tempResponseTime);
    }

}

