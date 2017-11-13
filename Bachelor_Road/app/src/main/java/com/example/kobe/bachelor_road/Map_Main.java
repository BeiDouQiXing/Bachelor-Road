package com.example.kobe.bachelor_road;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by kobe on 2017/11/6.
 */

public class Map_Main extends AppCompatActivity {

    private DatabaseManage databaseManage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseManage = new DatabaseManage(this);
        final int currentWeek = databaseManage.queryCHCurrentWeek();
        final int currentTime = databaseManage.queryCHCurrentTime();

        /*主界面人物信息显示*/
        TextView main_name = findViewById(R.id.main_name);
        main_name.setText(databaseManage.queryCHName());
        TextView main_vitality_value = findViewById(R.id.main_vitality_value);
        main_vitality_value.setText(String.valueOf(databaseManage.queryCHCurrentEnergy()));
        TextView main_study_value = findViewById(R.id.main_study_value);
        main_study_value.setText(String.valueOf(databaseManage.queryCHCredit()));
        TextView main_activity_point = findViewById(R.id.main_activity_point);
        main_activity_point.setText(String.valueOf(databaseManage.queryCHComprehensiveTest()));
        /*主界面时间信息显示*/
        TextView main_which_week = findViewById(R.id.main_which_week);
        main_which_week.setText("第" + String.valueOf(currentWeek) + "周");
        TextView main_which_noon = findViewById(R.id.main_which_noon);
        main_which_noon.setText(TimeTranslate.morningOrAfter(currentTime));
        TextView main_current_time = findViewById(R.id.main_current_time);
        main_current_time.setText(TimeTranslate.timeIntToString(currentTime));
        /*主界面部门按钮点击事件*/
        Button button_departmentButton = findViewById(R.id.main_department_button); //进入素拓，完成部门工作任务
        button_departmentButton.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 Intent intent = new Intent(Map_Main.this, Activity.class);
                                                 startActivity(intent);
                                             }
                                         }
        );
        /*主界面课程按钮点击事件*/
        Button button_building = findViewById(R.id.main_teaching_building_button);
        button_building.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map_Main.this, Timeable.class);
                startActivity(intent);
            }
        });
        /*主界面宿舍按钮点击事件*/
        Button main_dormitory_button = findViewById(R.id.main_dormitory_button);
        main_dormitory_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map_Main.this, Dorm.class);
                startActivity(intent);
            }
        });
    }
}
