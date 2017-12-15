package com.example.kobe.bachelor_road;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Dorm extends AppCompatActivity {

    final DatabaseManage databaseManage = new DatabaseManage(this);

    @Override
    public void onBackPressed() {
        /*主界面背景音乐播放*/
        Intent intentMusic1 = new Intent(Dorm.this, MyService1.class);
        startService(intentMusic1);

        /*宿舍背景音乐停止*/
        Intent intentMusic3 = new Intent(Dorm.this, MyService3.class);
        stopService(intentMusic3);

        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dorm);

        /*课程背景音乐播放*/
        Intent intentMusic3 = new Intent(Dorm.this, MyService3.class);
        startService(intentMusic3);

        Button dorm_run_out = findViewById(R.id.dorm_run_out);
        dorm_run_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*主界面背景音乐播放*/
                Intent intentMusic1 = new Intent(Dorm.this, MyService1.class);
                startService(intentMusic1);

                /*宿舍背景音乐停止*/
                Intent intentMusic3 = new Intent(Dorm.this, MyService3.class);
                stopService(intentMusic3);

                finish();
            }
        });
        findViewById(R.id.sleep).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (databaseManage.updateCHCurrentWeek(databaseManage.queryCHCurrentWeek() + 1) < 0) {
                    return;
                } else {
                    databaseManage.updateCHCurrentTime(8 * 60);
                    databaseManage.updateCHCurrentEnergy(100);
                    Toast.makeText(Dorm.this, "现在是第" + String.valueOf(databaseManage.queryCHCurrentWeek()) + "周", Toast.LENGTH_SHORT).show();

                    /*宿舍背景音乐停止*/
                    Intent intentMusic3 = new Intent(Dorm.this, MyService3.class);
                    stopService(intentMusic3);
                    
                    finish();
                }

            }
        });
    }
}
