package com.example.kobe.bachelor_road;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by kobe on 2017/11/8.
 */

public class Activity extends AppCompatActivity {

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity);

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
}
