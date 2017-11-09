package com.example.kobe.bachelor_road;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by kobe on 2017/11/8.
 */

//加入部门界面
public class Add_Department extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_department);


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
