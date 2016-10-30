package yongjyun.com.basicui01;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        context = Main3Activity.this;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(context, "나가지 마세요 ><", Toast.LENGTH_SHORT).show();
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] vibPattern = {100, 200, 300};

        vibrator.vibrate(vibPattern, 0);
    }
}
