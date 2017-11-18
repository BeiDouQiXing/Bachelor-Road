package com.example.kobe.bachelor_road;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Dorm extends AppCompatActivity {

    private int timeForReturn;
    private int weekForReturn;
    private int eneryForReturn;
    final DatabaseManage databaseManage = new DatabaseManage(this);

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("week", weekForReturn);
        intent.putExtra("time", timeForReturn);
        intent.putExtra("enery", eneryForReturn);
        setResult(RESULT_OK, intent);
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dorm);

        timeForReturn = databaseManage.queryCHCurrentTime();
        weekForReturn = databaseManage.queryCHCurrentWeek();
        eneryForReturn = databaseManage.queryCHCurrentEnergy();

        Button dorm_run_out = findViewById(R.id.dorm_run_out);
        dorm_run_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("week", weekForReturn);
                intent.putExtra("time", timeForReturn);
                intent.putExtra("enery", eneryForReturn);
                setResult(RESULT_OK, intent);
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

                    timeForReturn = databaseManage.queryCHCurrentTime();
                    weekForReturn = databaseManage.queryCHCurrentWeek();
                    eneryForReturn = databaseManage.queryCHCurrentEnergy();

                    Toast.makeText(Dorm.this, "现在是第" + String.valueOf(databaseManage.queryCHCurrentWeek()) + "周", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
