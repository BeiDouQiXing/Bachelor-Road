package com.example.kobe.bachelor_road;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class GraduationActivity extends AppCompatActivity {

    private DatabaseManage databaseManage=new DatabaseManage(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_ending_one);

        TextView stuName = findViewById(R.id.ending_name_one);
        stuName.setText(databaseManage.queryCHName());

        TextView level = findViewById(R.id.which_number);

        final double credit = databaseManage.queryCHCredit();
        final double activityPoint=databaseManage.queryCHComprehensiveTest();

        double result=credit*0.8+activityPoint*0.2;
        if(result>13.5){
            level.setText("一等奖");
        }else if(result>12){
            level.setText("二等奖");
        }else if(result>10.5){
            level.setText("三等奖");
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
