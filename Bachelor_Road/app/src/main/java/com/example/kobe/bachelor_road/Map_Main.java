package com.example.kobe.bachelor_road;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

/**
 * Created by kobe on 2017/11/6.
 */

public class Map_Main extends AppCompatActivity {

    private DatabaseManage databaseManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseManage = new DatabaseManage(this);
        final int currentWeek = databaseManage.queryCHCurrentWeek();
        final int currentTime = databaseManage.queryCHCurrentTime();
        DecimalFormat df = new DecimalFormat("#0.000");

        /*主界面人物信息显示*/
        TextView main_name = findViewById(R.id.main_name);
        main_name.setText(databaseManage.queryCHName());
        TextView main_vitality_value = findViewById(R.id.main_vitality_value);
        main_vitality_value.setText(String.valueOf(databaseManage.queryCHCurrentEnergy()));
        TextView main_study_value = findViewById(R.id.main_study_value);
        main_study_value.setText(String.valueOf(df.format(databaseManage.queryCHCredit())));
        TextView main_activity_point = findViewById(R.id.main_activity_point);
        main_activity_point.setText(String.valueOf(df.format(databaseManage.queryCHComprehensiveTest())));
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
                android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Map_Main.this);
                dialog.setTitle("提示");
                dialog.setMessage("只能在第一周选择一个部门\n");
                dialog.setCancelable(false);
                dialog.setNegativeButton("", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which) {} });
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which) {
                        if( databaseManage.queryCHCurrentWeek() == 1 && Add_Department.isAddmit(databaseManage) == false ) {
                            Intent intent = new Intent(Map_Main.this, Add_Department.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(Map_Main.this, Activity.class);
                            startActivityForResult(intent, 2);
                        }
                    } });
                dialog.show();
            }
        });

        /*主界面课程按钮点击事件*/
        Button button_building = findViewById(R.id.main_teaching_building_button);
        button_building.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Map_Main.this);
                dialog.setTitle("提示");
                dialog.setMessage("只能选择当前时间之后的课程\n时间将在完成课程后更新");
                dialog.setCancelable(false);
                dialog.setNegativeButton("", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which) {} });
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which) {
                        Intent intent = new Intent(Map_Main.this, Timeable.class);
                        startActivityForResult(intent, 1);
                    } });
                dialog.show();
            }
        });
        /*主界面宿舍按钮点击事件*/
        Button main_dormitory_button = findViewById(R.id.main_dormitory_button);
        main_dormitory_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map_Main.this, Dorm.class);
                startActivityForResult(intent, 3);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        DecimalFormat df = new DecimalFormat("#0.000");
        databaseManage = new DatabaseManage(this);

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    int enery = data.getIntExtra("enery", 0);
                    double credit = data.getDoubleExtra("credit", 0);
                    int time = data.getIntExtra("time", 0);

                    TextView main_vitality_value = findViewById(R.id.main_vitality_value);
                    main_vitality_value.setText(String.valueOf(enery));
                    TextView main_study_value = findViewById(R.id.main_study_value);
                    main_study_value.setText(String.valueOf(df.format(credit)));
                    TextView main_which_week = findViewById(R.id.main_which_week);
                    main_which_week.setText("第" + String.valueOf(databaseManage.queryCHCurrentWeek()) + "周");
                    TextView main_which_noon = findViewById(R.id.main_which_noon);
                    main_which_noon.setText(TimeTranslate.morningOrAfter(time));
                    TextView main_current_time = findViewById(R.id.main_current_time);
                    main_current_time.setText(TimeTranslate.timeIntToString(time));
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    int enery = data.getIntExtra("enery", 0);
                    double comprehensiveTest = data.getDoubleExtra("comprehensiveTest", 0);
                    int time = data.getIntExtra("time", 0);

                    TextView main_vitality_value = findViewById(R.id.main_vitality_value);
                    main_vitality_value.setText(String.valueOf(enery));
                    TextView main_activity_point = findViewById(R.id.main_activity_point);
                    main_activity_point.setText(String.valueOf(df.format(comprehensiveTest)));

                    TextView main_which_noon = findViewById(R.id.main_which_noon);
                    main_which_noon.setText(TimeTranslate.morningOrAfter(time));
                    TextView main_current_time = findViewById(R.id.main_current_time);
                    main_current_time.setText(TimeTranslate.timeIntToString(time));
                }
                break;
            case 3:
                int week = data.getIntExtra("week", 0);
                int time = data.getIntExtra("time", 0);
                int enery = data.getIntExtra("enery", 0);

                TextView main_vitality_value = findViewById(R.id.main_vitality_value);
                main_vitality_value.setText(String.valueOf(enery));
                TextView main_which_week = findViewById(R.id.main_which_week);
                main_which_week.setText("第" + String.valueOf(week) + "周");
                TextView main_which_noon = findViewById(R.id.main_which_noon);
                main_which_noon.setText(TimeTranslate.morningOrAfter(time));
                TextView main_current_time = findViewById(R.id.main_current_time);
                main_current_time.setText(TimeTranslate.timeIntToString(time));
                break;
            default:
                break;
        }

    }


}
