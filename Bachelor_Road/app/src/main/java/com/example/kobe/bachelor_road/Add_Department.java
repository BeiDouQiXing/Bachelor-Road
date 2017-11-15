package com.example.kobe.bachelor_road;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kobe on 2017/11/8.
 */

//加入部门界面
public class Add_Department extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_department);

        /*当前周为第一周时，可以选择部门，否则不可以*/
        DatabaseManage databaseManage = new DatabaseManage(this);
        if( databaseManage.queryCHCurrentWeek() != 1) {
            Toast.makeText(Add_Department.this,"当前时间不允许加入部门",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Add_Department.this,Activity.class);
            startActivity(intent);
            finish();
        }

        TextView [] textView = new TextView[5];
              textView[0] = (TextView)findViewById(R.id.add_department_one);
              textView[1] = (TextView)findViewById(R.id.add_department_two);
              textView[2] = (TextView)findViewById(R.id.add_department_three);
              textView[3] = (TextView)findViewById(R.id.add_department_four);
              textView[4] = (TextView)findViewById(R.id.add_department_five);

                DepartmentActivities [] departmentActivities = new DepartmentActivities[500];
                for(int i = 0;i<500;i++) {
                    departmentActivities[i] = new DepartmentActivities();
                }
                departmentActivities = databaseManage.queryJoinedDepartmentActivities();


        Button buttonToActivity =(Button)findViewById(R.id.add_department_activity);
        buttonToActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_Department.this,Activity.class);
                startActivity(intent);
                finish();
            }
        }
        );

        Button buttonToMyDepartment = findViewById(R.id.add_department_my_department);
        buttonToMyDepartment.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(Add_Department.this,MyDepartment.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }
        );


        Button buttonBackMain = findViewById(R.id.add_department_run_out);
        buttonBackMain.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    finish();
                                                }
                                            }
        );



    }




}
