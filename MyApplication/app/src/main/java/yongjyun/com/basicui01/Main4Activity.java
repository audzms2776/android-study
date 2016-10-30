package yongjyun.com.basicui01;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    private LinearLayout activity_main3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        activity_main3 = (LinearLayout) findViewById(R.id.activity_main3);
        TextView textView = new TextView(Main4Activity.this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        textView.setPadding(20, 10, 10, 10);
        textView.setTextColor(Color.parseColor("#FF7200"));
        textView.setTextSize(13);
        textView.setText("텍스트");
        activity_main3.addView(textView);
    }
}
