package yongjyun.com.basicui01;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button studyBtn, toastBtn, intentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studyBtn = (Button) findViewById(R.id.studyBtn);
        toastBtn = (Button) findViewById(R.id.toastBtn);
        intentBtn = (Button) findViewById(R.id.intentBtn);

        studyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("and study", "안드로이드 스터디입니다");
            }
        });

        toastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Toast~~~", Toast.LENGTH_SHORT).show();
            }
        });

        intentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    public void clickMenu(View view) {

        Intent intent = null;
        switch (view.getId()) {
            case R.id.menu_btn1:
                intent = new Intent(MainActivity.this, Main3Activity.class);
                break;
            case R.id.menu_btn2:
                intent = new Intent(MainActivity.this, Main4Activity.class);
                break;
            case R.id.menu_btn3:
                intent = new Intent(MainActivity.this, Main5Activity.class);
                break;
            case R.id.menu_btn4:
                intent = new Intent(MainActivity.this, Main6Activity.class);
                break;
            default:
                break;
        }

        startActivity(intent);
    }
}
