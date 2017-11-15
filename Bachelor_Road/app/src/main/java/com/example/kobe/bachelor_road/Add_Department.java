package com.example.kobe.bachelor_road;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by kobe on 2017/11/8.
 */

//加入部门界面
public class Add_Department extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_department);

        /*当前周为第一周时，可以选择部门，否则不可以*/
        final DatabaseManage databaseManage = new DatabaseManage(this);
        if( databaseManage.queryCHCurrentWeek() != 1) {
            Toast.makeText(Add_Department.this,"当前时间不允许加入部门",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Add_Department.this,Activity.class);
            startActivity(intent);
            finish();
        }
        if(isAddmit(databaseManage)==true) {
            Toast.makeText(Add_Department.this,"您已经完成了选择，准备新生周后的生活吧！",Toast.LENGTH_SHORT).show();
        }
        /*几个部门的加入*/
        final Button [] buttonAddDept = new Button[5];
        buttonAddDept[0] = (Button) findViewById(R.id.add_department_one);
        buttonAddDept[1] = (Button) findViewById(R.id.add_department_two);
        buttonAddDept[2] = (Button) findViewById(R.id.add_department_three);
        buttonAddDept[3] = (Button) findViewById(R.id.add_department_four);
        buttonAddDept[4] = (Button) findViewById(R.id.add_department_five);

        Department [] department = new Department[500];
        for(int i = 0;i<500;i++) {
            department[i] = new Department();
        }
        department = databaseManage.queryAllDepartment();
        buttonAddDept[0].setText(department[0].DName);
        buttonAddDept[1].setText(department[1].DName);
        buttonAddDept[2].setText(department[2].DName);
        buttonAddDept[3].setText(department[3].DName);
        buttonAddDept[4].setText(department[4].DName);



        /*跳转几个界面*/
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

        buttonAddDept[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAddmit(databaseManage) == true) {
                    Toast.makeText(Add_Department.this,"您已经完成了选择，准备新生周后的生活吧！",Toast.LENGTH_SHORT).show();
                    finish();
                }
                int need = databaseManage.updateDNameDIsJoinDepartment(buttonAddDept[0].getText().toString(), true);
                if(need == 0) {
                    Toast.makeText(Add_Department.this,"加入成功",Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Add_Department.this,"加入失败",Toast.LENGTH_SHORT).show();
                }
            }

        });
        buttonAddDept[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAddmit(databaseManage)==true) {
                    Toast.makeText(Add_Department.this,"您已经完成了选择，准备新生周后的生活吧！",Toast.LENGTH_SHORT).show();
                    finish();
                }
                int need = databaseManage.updateDNameDIsJoinDepartment(buttonAddDept[1].getText().toString(), true);
                if(need >0) {
                    Toast.makeText(Add_Department.this,"加入成功",Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Add_Department.this,"加入失败",Toast.LENGTH_SHORT).show();
                }

            }
        });
        buttonAddDept[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAddmit(databaseManage)==true) {
                    Toast.makeText(Add_Department.this,"您已经完成了选择，准备新生周后的生活吧！",Toast.LENGTH_SHORT).show();
                    finish();
                }
                int need = databaseManage.updateDNameDIsJoinDepartment(buttonAddDept[2].getText().toString(), true);
                if(need > 0) {
                    Toast.makeText(Add_Department.this,"加入成功",Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Add_Department.this,"加入失败",Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonAddDept[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAddmit(databaseManage)==true) {
                    Toast.makeText(Add_Department.this,"您已经完成了选择，准备新生周后的生活吧！",Toast.LENGTH_SHORT).show();
                    finish();
                }
                int need = databaseManage.updateDNameDIsJoinDepartment(buttonAddDept[3].getText().toString(), true);
                if(need > 0) {
                    Toast.makeText(Add_Department.this,"加入成功",Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Add_Department.this,"加入失败",Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonAddDept[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAddmit(databaseManage)==true) {
                    Toast.makeText(Add_Department.this,"您已经完成了选择，准备新生周后的生活吧！",Toast.LENGTH_SHORT).show();
                    finish();
                }
                int need = databaseManage.updateDNameDIsJoinDepartment(buttonAddDept[4].getText().toString(), true);
                if(need > 0) {
                    Toast.makeText(Add_Department.this,"加入成功",Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Add_Department.this,"加入失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
        /*查询部门是否有加入的部门*/
    public boolean isAddmit(DatabaseManage databaseManage){
        for(int i = 0;i<5;i++)
        {
            if(databaseManage.queryDidDIsJoinDepartment(i)==1)
                return true;
                else
                    continue;
        }
        return false;
    }



}
