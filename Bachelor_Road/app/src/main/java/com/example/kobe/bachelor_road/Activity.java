package com.example.kobe.bachelor_road;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kobe on 2017/11/8.
 */

public class Activity extends AppCompatActivity {

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity);

            DatabaseManage databaseManage = new DatabaseManage(this);
            /*将一周的任务放到任务框中*/
            Button[] textView = new Button[5];
            textView[0] = (Button)findViewById(R.id.activity_one);
            textView[1] = (Button)findViewById(R.id.activity_two);
            textView[2] = (Button)findViewById(R.id.activity_three);
            textView[3] = (Button)findViewById(R.id.activity_four);
            textView[4] = (Button)findViewById(R.id.activity_five);

            /*从数据库中获取部门活动内容*/
            DepartmentActivities [] departmentActivities = new DepartmentActivities[500];
            for(int i = 0;i<500;i++) {
                departmentActivities[i] = new DepartmentActivities();
            }
            departmentActivities = databaseManage.queryJoinedDepartmentActivities();

            textView[0].setText(TimeTranslate.timeIntToString(departmentActivities[0].DABeginTime) + "~"+ TimeTranslate.timeIntToString(departmentActivities[0].DAEndTime) + " " + departmentActivities[0].DAName);
            textView[1].setText(TimeTranslate.timeIntToString(departmentActivities[1].DABeginTime) + "~"+ TimeTranslate.timeIntToString(departmentActivities[1].DAEndTime) + departmentActivities[1].DAName);
            textView[2].setText(TimeTranslate.timeIntToString(departmentActivities[2].DABeginTime) + "~"+ TimeTranslate.timeIntToString(departmentActivities[2].DAEndTime) + departmentActivities[2].DAName);
            textView[3].setText(TimeTranslate.timeIntToString(departmentActivities[3].DABeginTime) + "~"+ TimeTranslate.timeIntToString(departmentActivities[3].DAEndTime) + departmentActivities[3].DAName);
            textView[4].setText(TimeTranslate.timeIntToString(departmentActivities[4].DABeginTime) + "~"+ TimeTranslate.timeIntToString(departmentActivities[4].DAEndTime) + departmentActivities[4].DAName);




            Button buttonToAddDepartment =(Button)findViewById(R.id.activity_add_department);//转入加入的部门
            buttonToAddDepartment.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {
                                                      Intent intent = new Intent(Activity.this,Add_Department.class);
                                                      startActivity(intent);
                                                      finish();
                                                  }
                                              }
            );

            Button buttonToMyDepartment = findViewById(R.id.activity_my_department);
            buttonToMyDepartment.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Intent intent = new Intent(Activity.this,MyDepartment.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }
            );


            Button buttonBackMain = findViewById(R.id.activity_run_out);
            buttonBackMain.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {
                                                      finish();
                                                  }
                                              }
            );

    }
    public int activitySize(DepartmentActivities [] departmentActivities) {
        int len = 0;
        for(int i = 0 ;i < departmentActivities.length;i++){
            if(departmentActivities[i] == null) {
                return len;
            }
            len++;
        }
        return len;
    }
}
