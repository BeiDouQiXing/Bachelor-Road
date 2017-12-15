package com.example.kobe.bachelor_road;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SleepActivity extends AppCompatActivity {
    final DatabaseManage databaseManage = new DatabaseManage(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sleep);

        findViewById(R.id.sleep_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseManage.updateCHCurrentTime(8 * 60);
                databaseManage.updateCHCurrentEnergy(100);
                Toast.makeText(SleepActivity.this, "现在是第" + String.valueOf(databaseManage.queryCHCurrentWeek()) + "周", Toast.LENGTH_SHORT).show();

                    /*宿舍背景音乐停止*/
                Intent intentMusic3 = new Intent(SleepActivity.this, MyService3.class);
                stopService(intentMusic3);

                finish();
            }
        });
    }
}
