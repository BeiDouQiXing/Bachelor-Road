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

//加入部门界面
public class Add_Department extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        /*主界面背景音乐播放*/
        Intent intentMusic1 = new Intent(Add_Department.this, MyService1.class);
        startService(intentMusic1);

        /*部门背景音乐停止*/
        Intent intentMusic4 = new Intent(Add_Department.this, MyService4.class);
        stopService(intentMusic4);

        finish();
    }

    final DatabaseManage databaseManage = new DatabaseManage(this);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_department);

        /*当前周为第一周时，可以选择部门，否则不可以*/
        final DatabaseManage databaseManage = new DatabaseManage(this);

        if(isAdmit(databaseManage) == false && databaseManage.queryCHCurrentWeek() != 1) {
            Toast.makeText(this, "您未加入任何部门",Toast.LENGTH_SHORT);
            finish();
        }

        if( databaseManage.queryCHCurrentWeek() != 1) {
            Toast.makeText(Add_Department.this,"当前时间不允许加入部门",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Add_Department.this,Activity.class);
            startActivity(intent);
            finish();
        }

        if(isAdmit(databaseManage) == true && databaseManage.queryCHCurrentWeek() == 1) {
            Toast.makeText(Add_Department.this,"您已经完成了选择，准备新生周后的生活吧！",Toast.LENGTH_SHORT).show();
        }

        /*部门背景音乐播放*/
        Intent intentMusic4 = new Intent(Add_Department.this, MyService4.class);
        startService(intentMusic4);

        /*几个部门的加入*/
        final Button [] buttonAddDept = new Button[5];
        buttonAddDept[0] =  findViewById(R.id.add_department_one);
        buttonAddDept[1] =  findViewById(R.id.add_department_two);
        buttonAddDept[2] =  findViewById(R.id.add_department_three);
        buttonAddDept[3] =  findViewById(R.id.add_department_four);
        buttonAddDept[4] =  findViewById(R.id.add_department_five);

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
        Button buttonToActivity =findViewById(R.id.add_department_activity);
        buttonToActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAdmit(databaseManage)) {
                    Intent intent = new Intent(Add_Department.this,Activity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Add_Department.this,"您尚未加入部门",Toast.LENGTH_SHORT).show();
                }

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

        /*离开按钮点击事件*/
        Button buttonBackMain = findViewById(R.id.add_department_run_out);
        buttonBackMain.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    /*主界面背景音乐播放*/
                                                    Intent intentMusic1 = new Intent(Add_Department.this, MyService1.class);
                                                    startService(intentMusic1);

                                                    /*部门背景音乐停止*/
                                                    Intent intentMusic4 = new Intent(Add_Department.this, MyService4.class);
                                                    stopService(intentMusic4);

                                                    finish();
                                                }
                                            }
        );

        buttonAddDept[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAdmit(databaseManage) == true) {
                    Toast.makeText(Add_Department.this,"您已经完成了选择，准备新生周后的生活吧！",Toast.LENGTH_SHORT).show();

                    /*主界面背景音乐播放*/
                    Intent intentMusic1 = new Intent(Add_Department.this, MyService1.class);
                    startService(intentMusic1);

                    /*部门背景音乐停止*/
                    Intent intentMusic4 = new Intent(Add_Department.this, MyService4.class);
                    stopService(intentMusic4);

                    finish();
                }
                int need = databaseManage.updateDNameDIsJoinDepartment(buttonAddDept[0].getText().toString(), true);
                if(need == 1) {
                    Toast.makeText(Add_Department.this,"加入成功",Toast.LENGTH_SHORT).show();
                    updateWeek();

                    /*主界面背景音乐播放*/
                    Intent intentMusic1 = new Intent(Add_Department.this, MyService1.class);
                    startService(intentMusic1);

                    /*部门背景音乐停止*/
                    Intent intentMusic4 = new Intent(Add_Department.this, MyService4.class);
                    stopService(intentMusic4);

                    finish();
                } else {
                    Toast.makeText(Add_Department.this,"加入失败",Toast.LENGTH_SHORT).show();
                }
            }

        });
        buttonAddDept[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAdmit(databaseManage)==true) {
                    Toast.makeText(Add_Department.this,"您已经完成了选择，准备新生周后的生活吧！",Toast.LENGTH_SHORT).show();

                    /*主界面背景音乐播放*/
                    Intent intentMusic1 = new Intent(Add_Department.this, MyService1.class);
                    startService(intentMusic1);

                    /*部门背景音乐停止*/
                    Intent intentMusic4 = new Intent(Add_Department.this, MyService4.class);
                    stopService(intentMusic4);

                    finish();
                }
                int need = databaseManage.updateDNameDIsJoinDepartment(buttonAddDept[1].getText().toString(), true);
                if(need == 1) {
                    Toast.makeText(Add_Department.this,"加入成功",Toast.LENGTH_SHORT).show();
                    updateWeek();

                    /*主界面背景音乐播放*/
                    Intent intentMusic1 = new Intent(Add_Department.this, MyService1.class);
                    startService(intentMusic1);

                    /*部门背景音乐停止*/
                    Intent intentMusic4 = new Intent(Add_Department.this, MyService4.class);
                    stopService(intentMusic4);

                    finish();
                } else {
                    Toast.makeText(Add_Department.this,"加入失败",Toast.LENGTH_SHORT).show();
                }

            }
        });
        buttonAddDept[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAdmit(databaseManage)==true) {
                    Toast.makeText(Add_Department.this,"您已经完成了选择，准备新生周后的生活吧！",Toast.LENGTH_SHORT).show();

                    /*主界面背景音乐播放*/
                    Intent intentMusic1 = new Intent(Add_Department.this, MyService1.class);
                    startService(intentMusic1);

                    /*部门背景音乐停止*/
                    Intent intentMusic4 = new Intent(Add_Department.this, MyService4.class);
                    stopService(intentMusic4);

                    finish();
                }
                int need = databaseManage.updateDNameDIsJoinDepartment(buttonAddDept[2].getText().toString(), true);
                if(need == 1) {
                    Toast.makeText(Add_Department.this,"加入成功",Toast.LENGTH_SHORT).show();
                    updateWeek();

                    /*主界面背景音乐播放*/
                    Intent intentMusic1 = new Intent(Add_Department.this, MyService1.class);
                    startService(intentMusic1);

                    /*部门背景音乐停止*/
                    Intent intentMusic4 = new Intent(Add_Department.this, MyService4.class);
                    stopService(intentMusic4);

                    finish();
                } else {
                    Toast.makeText(Add_Department.this,"加入失败",Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonAddDept[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAdmit(databaseManage)==true) {
                    Toast.makeText(Add_Department.this,"您已经完成了选择，准备新生周后的生活吧！",Toast.LENGTH_SHORT).show();

                    /*主界面背景音乐播放*/
                    Intent intentMusic1 = new Intent(Add_Department.this, MyService1.class);
                    startService(intentMusic1);

                    /*部门背景音乐停止*/
                    Intent intentMusic4 = new Intent(Add_Department.this, MyService4.class);
                    stopService(intentMusic4);

                    finish();
                }
                int need = databaseManage.updateDNameDIsJoinDepartment(buttonAddDept[3].getText().toString(), true);
                if(need == 1) {
                    Toast.makeText(Add_Department.this,"加入成功",Toast.LENGTH_SHORT).show();

                    updateWeek();

                    /*主界面背景音乐播放*/
                    Intent intentMusic1 = new Intent(Add_Department.this, MyService1.class);
                    startService(intentMusic1);

                    /*部门背景音乐停止*/
                    Intent intentMusic4 = new Intent(Add_Department.this, MyService4.class);
                    stopService(intentMusic4);

                    finish();
                } else {
                    Toast.makeText(Add_Department.this,"加入失败",Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonAddDept[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAdmit(databaseManage)==true) {
                    Toast.makeText(Add_Department.this,"您已经完成了选择，准备新生周后的生活吧！",Toast.LENGTH_SHORT).show();

                    /*主界面背景音乐播放*/
                    Intent intentMusic1 = new Intent(Add_Department.this, MyService1.class);
                    startService(intentMusic1);

                    /*部门背景音乐停止*/
                    Intent intentMusic4 = new Intent(Add_Department.this, MyService4.class);
                    stopService(intentMusic4);

                    finish();
                }
                int need = databaseManage.updateDNameDIsJoinDepartment(buttonAddDept[4].getText().toString(), true);
                if(need == 1) {
                    Toast.makeText(Add_Department.this,"加入成功",Toast.LENGTH_SHORT).show();

                    updateWeek();

                    /*主界面背景音乐播放*/
                    Intent intentMusic1 = new Intent(Add_Department.this, MyService1.class);
                    startService(intentMusic1);

                    /*部门背景音乐停止*/
                    Intent intentMusic4 = new Intent(Add_Department.this, MyService4.class);
                    stopService(intentMusic4);

                    finish();
                } else {
                    Toast.makeText(Add_Department.this,"加入失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        /*查询部门是否有加入的部门*/
    public static boolean isAdmit(DatabaseManage databaseManage){
        for(int i = 0;i<5;i++)
        {
            if(databaseManage.queryDidDIsJoinDepartment(i)==1)
                return true;
                else
                    continue;
        }
        return false;
    }

    private void updateWeek(){
        if (databaseManage.updateCHCurrentWeek(databaseManage.queryCHCurrentWeek() + 1) < 0) {
            return;
        } else {
            databaseManage.updateCHCurrentTime(8 * 60);
            Toast.makeText(Add_Department.this, "现在是第" + String.valueOf(databaseManage.queryCHCurrentWeek()) + "周", Toast.LENGTH_SHORT).show();
        }
    }


}
