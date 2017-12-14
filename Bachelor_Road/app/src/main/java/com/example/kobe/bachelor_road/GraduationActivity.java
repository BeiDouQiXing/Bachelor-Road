package com.example.kobe.bachelor_road;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class GraduationActivity extends AppCompatActivity {

    private DatabaseManage databaseManage=new DatabaseManage(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_ending_one);

        databaseManage = new DatabaseManage(this);

        ImageView prize = findViewById(R.id.prize);

        TextView stuName = findViewById(R.id.ending_name_one);
        stuName.setText(databaseManage.queryCHName());

        TextView main_vitality_value = findViewById(R.id.main_vitality_value);
        main_vitality_value.setText(String.valueOf(databaseManage.queryCHCurrentEnergy()));
        TextView main_study_value = findViewById(R.id.main_study_value);
        main_study_value.setText(String.valueOf(df.format(databaseManage.queryCHCredit())));
        TextView main_activity_point = findViewById(R.id.main_activity_point);
        main_activity_point.setText(String.valueOf(df.format(databaseManage.queryCHComprehensiveTest())));

        /*主界面时间信息显示*/
        TextView main_which_week = findViewById(R.id.main_which_week);
        main_which_week.setText("第" + String.valueOf(currentWeek) + "周");
        final double credit = databaseManage.queryCHCredit();

        if(credit>){

        }
    }
}
