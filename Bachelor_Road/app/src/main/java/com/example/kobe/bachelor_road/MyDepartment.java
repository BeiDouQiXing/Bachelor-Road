package com.example.kobe.bachelor_road;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by kobe on 2017/11/8.
 */

public class MyDepartment extends AppCompatActivity  {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_department);
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

        Button buttonToActivity =(Button)findViewById(R.id.my_department_activity);
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
                                                  finish();
                                              }
                                          }
        );


    }

}
