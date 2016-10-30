package yongjyun.com.basicui01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main6Activity extends AppCompatActivity {

    private Button submitBtn, cleanBtn;
    private EditText inputTxt;
    private TextView submitTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        cleanBtn = (Button) findViewById(R.id.cleanBtn);
        submitTxt = (TextView) findViewById(R.id.submitTxt);
        inputTxt = (EditText) findViewById(R.id.inputTxt);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = inputTxt.getText().toString();
                Log.i("입력된 값", data);
                submitTxt.setText(data);
            }
        });

        cleanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTxt.setText("");
                submitTxt.setText("");
            }
        });
    }
}
