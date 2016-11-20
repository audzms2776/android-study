package hyungsuu.com.naveropenroma;

import android.os.AsyncTask;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private EditText txtInput, resultTxt;
    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInput = (EditText) findViewById(R.id.txtInput);
        sendBtn = (Button) findViewById(R.id.sendBtn);
        resultTxt = (EditText) findViewById(R.id.resultTxt);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = txtInput.getText().toString();
                if (txt.equals("") || txt.equals(" ")) {
                    Toast.makeText(MainActivity.this, "이름을 입력해 주세요", Toast.LENGTH_SHORT).show();
                } else {
                    new MyAsync(txt).execute();
                }
            }
        });
    }

    public class MyAsync extends AsyncTask<Void, Void, String> {

        ArrayList<NameEntity> nameEntityArrayList = new ArrayList<>();
        String txt;

        public MyAsync(String txt) {
            this.txt = txt;
        }

        @Override
        protected String doInBackground(Void... params) {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(MyConstant.NAVER_URL + "query=" + txt)
                    .addHeader("X-Naver-Client-Id", MyConstant.X_Naver_Client_Id)
                    .addHeader("X-Naver-Client-Secret", MyConstant.X_Naver_Client_Secret)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    } else {
                        nameEntityArrayList = parseString(response.body().string(), txt);
                        final String[] result = {txt + "의 이름 변환 결과!! \n"};

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (nameEntityArrayList.size() == 0) {
                                    result[0] += "결과가 없습니다.";
                                } else {
                                    for (int i = 0; i < nameEntityArrayList.size(); i++) {
                                        NameEntity temp = nameEntityArrayList.get(i);
                                        result[0] += temp.getName() + " 이름은 " + temp.getScore() + "점 입니다 \n";
                                    }
                                }

                                resultTxt.setText(result[0]);
                            }
                        });
                    }
                }
            });

            return null;
        }
    }

    public ArrayList<NameEntity> parseString(String result, String txt) {

        ArrayList<NameEntity> nameEntities = new ArrayList<>();
        Log.e("ss", result);
        try {
            JSONArray jsonArray = new JSONObject(result).getJSONArray("aResult").getJSONObject(0).getJSONArray("aItems");

            int length = jsonArray.length();

            for (int i = 0; i < length; i++) {
                NameEntity tempEntity = new NameEntity();
                JSONObject tempObject = jsonArray.getJSONObject(i);
                tempEntity.setName(tempObject.getString("name"));
                tempEntity.setScore(Integer.parseInt(tempObject.getString("score")));
                nameEntities.add(tempEntity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return nameEntities;
    }
}
