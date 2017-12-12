package com.example.kobe.bachelor_road;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kobe on 2017/11/8.
 */

public class Activity extends AppCompatActivity {
    private int eneryForReturn;
    private double comprehensiveTestForReturn;
    private int timeForReturn;

    @Override
    public void onBackPressed() {
        /*主界面背景音乐播放*/
        Intent intentMusic1 = new Intent(Activity.this, MyService1.class);
        startService(intentMusic1);

        /*部门背景音乐停止*/
        Intent intentMusic4 = new Intent(Activity.this, MyService4.class);
        stopService(intentMusic4);

        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        /*部门背景音乐播放*/
        Intent intentMusic4 = new Intent(Activity.this, MyService4.class);
        startService(intentMusic4);

        final DatabaseManage databaseManage = new DatabaseManage(this);

        eneryForReturn = databaseManage.queryCHCurrentEnergy();
        comprehensiveTestForReturn = databaseManage.queryCHCredit();
        timeForReturn = databaseManage.queryCHCurrentTime();

        /*将一周的任务放到任务框中*/
        final Button[] buttonActivity = new Button[5];
        buttonActivity[0] = (Button)findViewById(R.id.activity_one);
        buttonActivity[1] = (Button)findViewById(R.id.activity_two);
        buttonActivity[2] = (Button)findViewById(R.id.activity_three);
        buttonActivity[3] = (Button)findViewById(R.id.activity_four);
        buttonActivity[4] = (Button)findViewById(R.id.activity_five);

        /*从数据库中获取部门活动内容*/
        DepartmentActivities [] departmentActivities = new DepartmentActivities[500];
        for(int i = 0;i<500;i++) {
            departmentActivities[i] = new DepartmentActivities();
        }
        departmentActivities = databaseManage.queryJoinedDepartmentCurrentWeekActivities();
        /*判定是否为空*/
       if( departmentActivities.length == 0 ) {
            Toast.makeText(Activity.this,"当前不存在部门工作，请查询是否加入部门",Toast.LENGTH_SHORT);
            finish();
        } else {
           buttonActivity[0].setText(TimeTranslate.timeIntToString(departmentActivities[0].DABeginTime) + "~" + TimeTranslate.timeIntToString(departmentActivities[0].DAEndTime) + " " + departmentActivities[0].DAName);
           buttonActivity[1].setText(TimeTranslate.timeIntToString(departmentActivities[1].DABeginTime) + "~" + TimeTranslate.timeIntToString(departmentActivities[1].DAEndTime) + " " + departmentActivities[1].DAName);
           buttonActivity[2].setText(TimeTranslate.timeIntToString(departmentActivities[2].DABeginTime) + "~" + TimeTranslate.timeIntToString(departmentActivities[2].DAEndTime) + " " + departmentActivities[2].DAName);
           buttonActivity[3].setText(TimeTranslate.timeIntToString(departmentActivities[3].DABeginTime) + "~" + TimeTranslate.timeIntToString(departmentActivities[3].DAEndTime) + " " + departmentActivities[3].DAName);
           buttonActivity[4].setText(TimeTranslate.timeIntToString(departmentActivities[4].DABeginTime) + "~" + TimeTranslate.timeIntToString(departmentActivities[4].DAEndTime) + " " + departmentActivities[4].DAName);
       }

        buttonActivity[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*确定当前时间*/
                int currentTime = databaseManage.queryCHCurrentTime();
                /*判断活动是否存在*/
                if (!buttonActivity[0].getText().equals("")) {
                    DepartmentActivities [] departmentActivities = new DepartmentActivities[500];
                    for(int i = 0;i<500;i++) {
                        departmentActivities[i] = new DepartmentActivities();
                    }
                    departmentActivities = databaseManage.queryJoinedDepartmentCurrentWeekActivities();
                    /*判断当前时间能否进行该活动*/
                    if (currentTime >  departmentActivities[0].DABeginTime) {
                        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Activity.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("不能选择当前时间之前的活动");
                        dialog.setCancelable(false);
                        dialog.setNegativeButton("", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.show();
                    } else {
                        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Activity.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("消耗活力值：" + departmentActivities[0].DAEnergy + "\n"
                                + "获得综测：" + departmentActivities[0].DAComprehensiveTest);
                        dialog.setCancelable(false);
                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {
                                int CHCurrentEnergy = databaseManage.queryCHCurrentEnergy();
                                double  CHComprehensiveTest = databaseManage.queryCHComprehensiveTest();

                                DepartmentActivities [] departmentActivities = new DepartmentActivities[500];
                                for(int i = 0;i<500;i++) {
                                    departmentActivities[i] = new DepartmentActivities();
                                }
                                departmentActivities = databaseManage.queryJoinedDepartmentCurrentWeekActivities();
                                /*数据库数据更新*/
                                int i = databaseManage.updateCHCurrentEnergy(CHCurrentEnergy - departmentActivities[0].DAEnergy);
                                i = databaseManage.updateCHComprehensiveTest( CHComprehensiveTest + departmentActivities[0].DAComprehensiveTest);
                                i = databaseManage.updateCHCurrentTime(departmentActivities[0].DAEndTime);

                                /*主界面活力值综测及时间更新*/
                                eneryForReturn = databaseManage.queryCHCurrentEnergy();
                                comprehensiveTestForReturn = databaseManage.queryCHComprehensiveTest();
                                timeForReturn = databaseManage.queryCHCurrentTime();
                                Intent intent = new Intent();
                                intent.putExtra("enery", eneryForReturn);
                                intent.putExtra("comprehensiveTest", comprehensiveTestForReturn);
                                intent.putExtra("time", timeForReturn);
                                setResult(RESULT_OK, intent);

                                Toast.makeText(Activity.this, "活动已完成~", Toast.LENGTH_SHORT).show();

                                } });
                            dialog.show();
                        }
                    }
                }
            });

        buttonActivity[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*确定当前时间*/
                int currentTime = databaseManage.queryCHCurrentTime();
                /*判断活动是否存在*/
                if (!buttonActivity[1].getText().equals("")) {
                    DepartmentActivities [] departmentActivities = new DepartmentActivities[500];
                    for(int i = 0;i<500;i++) {
                        departmentActivities[i] = new DepartmentActivities();
                    }
                    departmentActivities = databaseManage.queryJoinedDepartmentActivities();
                    /*判断当前时间能否进行该活动*/
                    if (currentTime >  departmentActivities[1].DABeginTime) {
                        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Activity.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("不能选择当前时间之前的活动");
                        dialog.setCancelable(false);
                        dialog.setNegativeButton("", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.show();
                    } else {
                        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Activity.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("消耗活力值：" + departmentActivities[1].DAEnergy + "\n"
                                + "获得综测：" + departmentActivities[1].DAComprehensiveTest);
                        dialog.setCancelable(false);
                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {
                                int CHCurrentEnergy = databaseManage.queryCHCurrentEnergy();
                                double  CHComprehensiveTest = databaseManage.queryCHComprehensiveTest();

                                DepartmentActivities [] departmentActivities = new DepartmentActivities[500];
                                for(int i = 0;i<500;i++) {
                                    departmentActivities[i] = new DepartmentActivities();
                                }
                                departmentActivities = databaseManage.queryJoinedDepartmentCurrentWeekActivities();
                                /*数据库数据更新*/
                                int i = databaseManage.updateCHCurrentEnergy(CHCurrentEnergy - departmentActivities[1].DAEnergy);
                                i = databaseManage.updateCHComprehensiveTest( CHComprehensiveTest + departmentActivities[1].DAComprehensiveTest);
                                i = databaseManage.updateCHCurrentTime(departmentActivities[1].DAEndTime);

                                /*主界面活力值综测及时间更新*/
                                eneryForReturn = databaseManage.queryCHCurrentEnergy();
                                comprehensiveTestForReturn = databaseManage.queryCHComprehensiveTest();
                                timeForReturn = databaseManage.queryCHCurrentTime();
                                Intent intent = new Intent();
                                intent.putExtra("enery", eneryForReturn);
                                intent.putExtra("comprehensiveTest", comprehensiveTestForReturn);
                                intent.putExtra("time", timeForReturn);
                                setResult(RESULT_OK, intent);

                                Toast.makeText(Activity.this, "活动已完成~", Toast.LENGTH_SHORT).show();

                            } });
                        dialog.show();
                    }
                }
            }
        });
        buttonActivity[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*确定当前时间*/
                int currentTime = databaseManage.queryCHCurrentTime();
                /*判断活动是否存在*/
                if (!buttonActivity[2].getText().equals("")) {
                    DepartmentActivities [] departmentActivities = new DepartmentActivities[500];
                    for(int i = 0;i<500;i++) {
                        departmentActivities[i] = new DepartmentActivities();
                    }
                    departmentActivities = databaseManage.queryJoinedDepartmentCurrentWeekActivities();
                    /*判断当前时间能否进行该活动*/
                    if (currentTime >  departmentActivities[2].DABeginTime) {
                        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Activity.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("不能选择当前时间之前的活动");
                        dialog.setCancelable(false);
                        dialog.setNegativeButton("", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.show();
                    } else {
                        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Activity.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("消耗活力值：" + departmentActivities[2].DAEnergy + "\n"
                                + "获得综测：" + departmentActivities[2].DAComprehensiveTest);
                        dialog.setCancelable(false);
                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {
                                int CHCurrentEnergy = databaseManage.queryCHCurrentEnergy();
                                double  CHComprehensiveTest = databaseManage.queryCHComprehensiveTest();

                                DepartmentActivities [] departmentActivities = new DepartmentActivities[500];
                                for(int i = 0;i<500;i++) {
                                    departmentActivities[i] = new DepartmentActivities();
                                }
                                departmentActivities = databaseManage.queryJoinedDepartmentCurrentWeekActivities();
                                /*数据库数据更新*/
                                int i = databaseManage.updateCHCurrentEnergy(CHCurrentEnergy - departmentActivities[2].DAEnergy);
                                i = databaseManage.updateCHComprehensiveTest( CHComprehensiveTest + departmentActivities[2].DAComprehensiveTest);
                                i = databaseManage.updateCHCurrentTime(departmentActivities[2].DAEndTime);

                                /*主界面活力值综测及时间更新*/
                                eneryForReturn = databaseManage.queryCHCurrentEnergy();
                                comprehensiveTestForReturn = databaseManage.queryCHComprehensiveTest();
                                timeForReturn = databaseManage.queryCHCurrentTime();
                                Intent intent = new Intent();
                                intent.putExtra("enery", eneryForReturn);
                                intent.putExtra("comprehensiveTest", comprehensiveTestForReturn);
                                intent.putExtra("time", timeForReturn);
                                setResult(RESULT_OK, intent);

                                Toast.makeText(Activity.this, "活动已完成~", Toast.LENGTH_SHORT).show();

                            } });
                        dialog.show();
                    }
                }
            }
        });

        buttonActivity[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*确定当前时间*/
                int currentTime = databaseManage.queryCHCurrentTime();
                /*判断活动是否存在*/
                if (!buttonActivity[3].getText().equals("")) {
                    DepartmentActivities [] departmentActivities = new DepartmentActivities[500];
                    for(int i = 0;i<500;i++) {
                        departmentActivities[i] = new DepartmentActivities();
                    }
                    departmentActivities = databaseManage.queryJoinedDepartmentCurrentWeekActivities();
                    /*判断当前时间能否进行该活动*/
                    if (currentTime >  departmentActivities[3].DABeginTime) {
                        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Activity.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("不能选择当前时间之前的活动");
                        dialog.setCancelable(false);
                        dialog.setNegativeButton("", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.show();
                    } else {
                        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Activity.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("消耗活力值：" + departmentActivities[3].DAEnergy + "\n"
                                + "获得综测：" + departmentActivities[3].DAComprehensiveTest);
                        dialog.setCancelable(false);
                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {
                                int CHCurrentEnergy = databaseManage.queryCHCurrentEnergy();
                                double  CHComprehensiveTest = databaseManage.queryCHComprehensiveTest();

                                DepartmentActivities [] departmentActivities = new DepartmentActivities[500];
                                for(int i = 0;i<500;i++) {
                                    departmentActivities[i] = new DepartmentActivities();
                                }
                                departmentActivities = databaseManage.queryJoinedDepartmentCurrentWeekActivities();
                                /*数据库数据更新*/
                                int i = databaseManage.updateCHCurrentEnergy(CHCurrentEnergy - departmentActivities[3].DAEnergy);
                                i = databaseManage.updateCHComprehensiveTest( CHComprehensiveTest + departmentActivities[3].DAComprehensiveTest);
                                i = databaseManage.updateCHCurrentTime(departmentActivities[3].DAEndTime);

                                /*主界面活力值综测及时间更新*/
                                eneryForReturn = databaseManage.queryCHCurrentEnergy();
                                comprehensiveTestForReturn = databaseManage.queryCHComprehensiveTest();
                                timeForReturn = databaseManage.queryCHCurrentTime();
                                Intent intent = new Intent();
                                intent.putExtra("enery", eneryForReturn);
                                intent.putExtra("comprehensiveTest", comprehensiveTestForReturn);
                                intent.putExtra("time", timeForReturn);
                                setResult(RESULT_OK, intent);

                                Toast.makeText(Activity.this, "活动已完成~", Toast.LENGTH_SHORT).show();


                            } });
                        dialog.show();
                    }
                }
            }
        });

        buttonActivity[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*确定当前时间*/
                int currentTime = databaseManage.queryCHCurrentTime();
                /*判断活动是否存在*/
                if (!buttonActivity[4].getText().equals("")) {
                    DepartmentActivities [] departmentActivities = new DepartmentActivities[500];
                    for(int i = 0;i<500;i++) {
                        departmentActivities[i] = new DepartmentActivities();
                    }
                    departmentActivities = databaseManage.queryJoinedDepartmentCurrentWeekActivities();
                    /*判断当前时间能否进行该活动*/
                    if (currentTime >  departmentActivities[4].DABeginTime) {
                        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Activity.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("不能选择当前时间之前的活动");
                        dialog.setCancelable(false);
                        dialog.setNegativeButton("", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.show();
                    } else {
                        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Activity.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("消耗活力值：" + departmentActivities[4].DAEnergy + "\n"
                                + "获得综测：" + departmentActivities[4].DAComprehensiveTest);
                        dialog.setCancelable(false);
                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {
                                int CHCurrentEnergy = databaseManage.queryCHCurrentEnergy();
                                double  CHComprehensiveTest = databaseManage.queryCHComprehensiveTest();

                                DepartmentActivities [] departmentActivities = new DepartmentActivities[500];
                                for(int i = 0;i<500;i++) {
                                    departmentActivities[i] = new DepartmentActivities();
                                }
                                departmentActivities = databaseManage.queryJoinedDepartmentCurrentWeekActivities();
                                /*数据库数据更新*/
                                int i = databaseManage.updateCHCurrentEnergy(CHCurrentEnergy - departmentActivities[4].DAEnergy);
                                i = databaseManage.updateCHComprehensiveTest( CHComprehensiveTest + departmentActivities[4].DAComprehensiveTest);
                                i = databaseManage.updateCHCurrentTime(departmentActivities[4].DAEndTime);

                                /*主界面活力值综测及时间更新*/
                                eneryForReturn = databaseManage.queryCHCurrentEnergy();
                                comprehensiveTestForReturn = databaseManage.queryCHComprehensiveTest();
                                timeForReturn = databaseManage.queryCHCurrentTime();
                                Intent intent = new Intent();
                                intent.putExtra("enery", eneryForReturn);
                                intent.putExtra("comprehensiveTest", comprehensiveTestForReturn);
                                intent.putExtra("time", timeForReturn);
                                setResult(RESULT_OK, intent);

                                Toast.makeText(Activity.this, "活动已完成~", Toast.LENGTH_SHORT).show();
                                
                            } });
                        dialog.show();
                    }
                }
            }
        });

        Button buttonToAddDepartment =(Button)findViewById(R.id.activity_add_department);//转入加入的部门
        buttonToAddDepartment.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {
                                                      Intent intent = new Intent(Activity.this,Add_Department.class);
                                                      startActivity(intent);
                                                      finish();
                                                  }
                                              });

        Button buttonToMyDepartment = findViewById(R.id.activity_my_department);
        buttonToMyDepartment.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Intent intent = new Intent(Activity.this,MyDepartment.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                });


        Button buttonBackMain = findViewById(R.id.activity_run_out);
        buttonBackMain.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {
                                                      /*主界面背景音乐播放*/
                                                      Intent intentMusic1 = new Intent(Activity.this, MyService1.class);
                                                      startService(intentMusic1);

                                                      /*部门背景音乐停止*/
                                                      Intent intentMusic4 = new Intent(Activity.this, MyService4.class);
                                                      stopService(intentMusic4);

                                                      finish();
                                                  }
                                              });

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
