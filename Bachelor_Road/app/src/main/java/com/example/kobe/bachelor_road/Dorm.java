package com.example.kobe.bachelor_road;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
                    Intent sleep=new Intent(Dorm.this, SleepActivity.class);
                    startActivity(sleep);
                    finish();
                }

            }
        });

        findViewById(R.id.game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent game=new Intent(Dorm.this, GameActivity.class);
                    startActivity(game);
            }
        });
    }
}
