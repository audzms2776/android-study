package hyungsuu.com.myapplication;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static hyungsuu.com.myapplication.R.id.headerBtn;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setDrawer();
    }

    public void setDrawer() {
        DrawerLayout drawerLayout;
        NavigationView navigationView;
        Button headerBtn;

        navigationView = (NavigationView) findViewById(R.id.navigationView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        View headerView = getLayoutInflater().inflate(R.layout.header_main, drawerLayout, false);
        navigationView.addHeaderView(headerView);

        headerBtn = (Button) headerView.findViewById(R.id.headerBtn);
        headerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("headerBtn", "click");
            }
        });
    }
}
