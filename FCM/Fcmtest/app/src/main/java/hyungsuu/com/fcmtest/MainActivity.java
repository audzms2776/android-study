package hyungsuu.com.fcmtest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button mainBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("news");
        FirebaseInstanceId.getInstance().getToken();

        mainBtn = (Button) findViewById(R.id.mainBtn);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ss", "sss");

                new AsyncTask<Void, Void, String>() {
                    @Override
                    protected String doInBackground(Void... params) {
                        OkHttpClient client = new OkHttpClient();

                        Request request = new Request.Builder()
                                .url(MyConstant.URL + "messaging?target=0")
                                .get()
                                .build();

                        try {
                            Log.e("res", client.newCall(request).execute().body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }
                }.execute();
            }
        });
    }
}
