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

public class MyDepartment extends AppCompatActivity  {
    @Override
    public void onBackPressed() {
        /*主界面背景音乐播放*/
        Intent intentMusic1 = new Intent(MyDepartment.this, MyService1.class);
        startService(intentMusic1);

        /*部门背景音乐停止*/
        Intent intentMusic4 = new Intent(MyDepartment.this, MyService4.class);
        stopService(intentMusic4);

        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_department);
        TextView textView = findViewById(R.id.my_department_name);
        final DatabaseManage databaseManage =new DatabaseManage(this);
            if(isAddmit(databaseManage)== null )
                textView.setText("您还没有选择部门");
            else
                textView.setText(isAddmit(databaseManage).toString());

        Button buttonToAddDepartment =(Button)findViewById(R.id.my_department_add_department);//转入加入的部门
        buttonToAddDepartment.setOnClickListener(new View.OnClickListener() {
                                                     @Override
                                                     public void onClick(View v) {
                                                         Intent intent = new Intent(MyDepartment.this,Add_Department.class);
                                                         startActivity(intent);
                                                         finish();
                                                     }
                                                 }
        );

        Button buttonToActivity =findViewById(R.id.my_department_activity);
        buttonToActivity.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(MyDepartment.this,Activity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }
        );

        Button buttonBackMain = findViewById(R.id.my_department_run_out);
        buttonBackMain.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  /*主界面背景音乐播放*/
                                                  Intent intentMusic1 = new Intent(MyDepartment.this, MyService1.class);
                                                  startService(intentMusic1);

                                                    /*部门背景音乐停止*/
                                                  Intent intentMusic4 = new Intent(MyDepartment.this, MyService4.class);
                                                  stopService(intentMusic4);

                                                  finish();
                                              }
                                          }
        );
    }
    /*查询加入的部门*/
    public String isAddmit(DatabaseManage databaseManage){
        for(int i = 1;i<6;i++)
        {
            if(databaseManage.queryDidDIsJoinDepartment(i)==1) {
                return databaseManage.queryDidDepartment(i).DName.toString();
            }
            else if(databaseManage.queryDidDIsJoinDepartment(i) == 0 )
                continue;
            else if(databaseManage.queryDidDIsJoinDepartment(i) == -1)
                Toast.makeText(this,"查询失败",Toast.LENGTH_SHORT);
        }
        return null;
    }
}
