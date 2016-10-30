package yongjyun.com.basicui01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;

public class Main5Activity extends AppCompatActivity {

    private TimePicker timePicker;
    private TextView selectTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        selectTime = (TextView) findViewById(R.id.selectTime);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Log.e("time", hourOfDay + " " + minute);
                Log.e("tttt", view.toString());
                String time = hourOfDay + "시 " + minute + "분";
                selectTime.setText(time);
            }
        });


    }
}
