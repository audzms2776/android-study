package com.example.user.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    protected ImageView captchaImage;
    protected TextView resultTxt;
    private EditText inputTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        captchaImage = (ImageView) findViewById(R.id.captchaImage);
        inputTxt = (EditText) findViewById(R.id.inputTxt);
        resultTxt = (TextView) findViewById(R.id.resultTxt);
    }

    public void onCaptchaLoading(View view) {
        new KeyGenAsync(MainActivity.this).execute();
        cleanTxtImage(MainActivity.this);
    }

    public void onCaptchaSending(View view) {

        String txt = inputTxt.getText().toString();
        if (txt.equals("") || txt.equals(" ")) {
            Toast.makeText(MainActivity.this, "이름을 입력해 주세요", Toast.LENGTH_SHORT).show();
        } else {
            cleanTxtImage(MainActivity.this);
            new CheckInputAsync(MainActivity.this, txt).execute();
        }
    }

    public void cleanTxtImage(Context context) {
        MainActivity activity = (MainActivity) context;
        activity.inputTxt.setText("");
        activity.captchaImage.setImageDrawable(null);
        activity.resultTxt.setText("");
    }
}
