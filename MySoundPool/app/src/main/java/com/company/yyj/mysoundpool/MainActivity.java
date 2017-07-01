package com.company.yyj.mysoundpool;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int sid;
    private SoundPool soundPool;
    private boolean soundFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.startBtn).setOnClickListener(v -> initTask());
        findViewById(R.id.endBtn).setOnClickListener(v -> stopMusic());
    }

    private void initTask() {
        if (soundFlag) {
            soundPool.stop(sid);
            soundPool.release();
        }

        Toast.makeText(this, "Toast Message", Toast.LENGTH_SHORT).show();
        Log.e("init tag", "sound pool ex!");

        soundPool = new SoundPool(1, AudioManager.STREAM_ALARM, 0);
        sid = soundPool.load(this, R.raw.above_it_all, 1);

        soundPool.setOnLoadCompleteListener((soundPool1, sampleId, status)
                -> soundPool.play(sid, 0.4f, 0.4f, 1, -1, 1));
        soundFlag = true;
    }

    private void stopMusic() {
        if (soundFlag) {
            soundPool.stop(sid);
            soundPool.release();
            soundFlag = false;
        }
    }
}
