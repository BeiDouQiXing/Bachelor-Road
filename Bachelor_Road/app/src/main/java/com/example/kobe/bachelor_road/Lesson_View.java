package com.example.kobe.bachelor_road;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Lesson_View extends AppCompatActivity {

    private String week;
    private String CName;
    private int baseTime;
    private int currentTime;
    private double CHCredit;
    private int CHCurrentEnergy;
    private int eachClassEnergy;
    private double eachClassCredit;
    private DatabaseManage databaseManage;

    private int eneryForReturn;
    private double creditForReturn;
    private int timeForReturn;
    private int takeTimes = 0;

    public int getCHid(int currentWeek, String week, int cnt) {
        int CHid = (currentWeek - 2) * 20;

        if (week.equals("Mon")) {
            CHid += 0 * 4;
        } else if (week.equals("Tues")) {
            CHid += 1 * 4;
        } else if (week.equals("Wed")) {
            CHid += 2 * 4;
        } else if (week.equals("Thu")) {
            CHid += 3 * 4;
        } else {
            CHid += 4 * 4;
        }

        return  CHid + cnt;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("enery", eneryForReturn);
        intent.putExtra("credit", creditForReturn);
        intent.putExtra("time", timeForReturn);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_view);




        final DecimalFormat df = new DecimalFormat("#0.000");
        databaseManage = new DatabaseManage(this);

        eneryForReturn = databaseManage.queryCHCurrentEnergy();
        creditForReturn = databaseManage.queryCHCredit();
        timeForReturn = databaseManage.queryCHCurrentTime();

        Intent intent = getIntent();
        /*确定当前是周几*/
        week = intent.getStringExtra("week");
        if (week.equals("Mon")) {
            baseTime = 0;
        } else if (week.equals("Tues")) {
            baseTime = (24 * 60);
        } else if (week.equals("Wed")) {
            baseTime = (2 * 24 * 60);
        } else if (week.equals("Thu")) {
            baseTime = (3 * 24 * 60);
        } else {
            baseTime = (4 * 24 * 60);
        }

        TextView lesson_week = findViewById(R.id.lesson_week);
        lesson_week.setText(week);

        /*退出按钮点击操作*/
        ImageView lesson_view_run_out = findViewById(R.id.lesson_view_run_out);
        lesson_view_run_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("enery", eneryForReturn);
                intent.putExtra("credit", creditForReturn);
                intent.putExtra("time", timeForReturn);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        /*第一节课点击事件*/
        final Button first_lesson = findViewById(R.id.first_lesson);
        first_lesson.setText(intent.getStringExtra("first_lesson"));
        first_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*判断课程是否存在*/
                if (!first_lesson.getText().equals("")) {
                    currentTime = baseTime + (8 * 60) + 20;
                    /*判断当前时间能否进行该课程*/
                    if (currentTime < databaseManage.queryCHCurrentTime()) {
                        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Lesson_View.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("不能选择当前时间之前的课程");
                        dialog.setCancelable(false);
                        dialog.setNegativeButton("", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.show();
                    } else {
                        CName = first_lesson.getText().toString();
                        eachClassCredit = databaseManage.queryCNameCEachClassCredit(CName);
                        eachClassEnergy = databaseManage.queryCNameCEachClassEnergy(CName);
                        //特殊判断C语言课程
                        if (CName.equals("高级语言程序设计")) {
                            CHCurrentEnergy = databaseManage.queryCHCurrentEnergy();
                            CHCredit = databaseManage.queryCHCredit();
                            int newEnergy = CHCurrentEnergy - eachClassEnergy;

                            /*当前活力值不足以进行课程*/
                            if (newEnergy < 0) {
                                android.app.AlertDialog.Builder errorDialog = new android.app.AlertDialog.Builder(Lesson_View.this);
                                errorDialog.setTitle("提示");
                                errorDialog.setMessage("活力值不足，不能进行该课程！");
                                errorDialog.setCancelable(false);
                                errorDialog.setNegativeButton("", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                errorDialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog,int which) {
                                        Intent intent = new Intent();
                                        intent.putExtra("enery", eneryForReturn);
                                        intent.putExtra("credit", creditForReturn);
                                        intent.putExtra("time", timeForReturn);
                                        setResult(RESULT_OK, intent);

                                        finish();
                                    }
                                });
                                errorDialog.show();
                            } else {
                                int cnt = databaseManage.queryCNameAttendClassNumber(CName);
                                int CHid = getCHid(databaseManage.queryCHCurrentWeek(), week, 1);

                                int flg = databaseManage.updateCHCidCHCIsAttendClass(CHid, true);
                                if (flg != 1) {
                                    Toast.makeText(Lesson_View.this, "调出课程失败！", Toast.LENGTH_SHORT).show();
                                }

                                switch (cnt) {
                                    case 0 :
                                        Intent intent1 = new Intent(Lesson_View.this, Classroom_Program_C_One.class);
                                        startActivity(intent1);
                                        break;
                                    case 1 :
                                        Intent intent2 = new Intent(Lesson_View.this, Classroom_Program_C_Two.class);
                                        startActivity(intent2);
                                        break;
                                    case 2 :
                                        Intent intent3 = new Intent(Lesson_View.this, Classroom_Program_C_Three.class);
                                        startActivity(intent3);
                                        break;
                                    case 3:
                                        break;
                                }

                                int i = databaseManage.updateCHCurrentEnergy(CHCurrentEnergy - eachClassEnergy);
                                i = databaseManage.updateCHCredit(CHCredit + eachClassCredit);
                                i = databaseManage.updateCHCurrentTime(baseTime + (10 * 60));

                                eneryForReturn = databaseManage.queryCHCurrentEnergy();
                                creditForReturn = databaseManage.queryCHCredit();
                                timeForReturn = databaseManage.queryCHCurrentTime();
                                Intent intent = new Intent();
                                intent.putExtra("enery", eneryForReturn);
                                intent.putExtra("credit", creditForReturn);
                                intent.putExtra("time", timeForReturn);
                                setResult(RESULT_OK, intent);
                            }

                        } else {
                            android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Lesson_View.this);
                            dialog.setTitle("提示");
                            dialog.setMessage("消耗活力值：" + eachClassEnergy + "\n"
                                    + "获得学分：" + df.format(eachClassCredit) );
                            dialog.setCancelable(false);
                            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog,int which) {} });
                            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog,int which) {
                                    CHCurrentEnergy = databaseManage.queryCHCurrentEnergy();
                                    CHCredit = databaseManage.queryCHCredit();
                                    int newEnergy = CHCurrentEnergy - eachClassEnergy;

                                    /*当前活力值不足以进行课程*/
                                    if (newEnergy < 0) {
                                        android.app.AlertDialog.Builder errorDialog = new android.app.AlertDialog.Builder(Lesson_View.this);
                                        errorDialog.setTitle("提示");
                                        errorDialog.setMessage("活力值不足，不能进行该课程！");
                                        errorDialog.setCancelable(false);
                                        errorDialog.setNegativeButton("", new DialogInterface.OnClickListener(){
                                            public void onClick(DialogInterface dialog,int which) {} });
                                        errorDialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                                            public void onClick(DialogInterface dialog,int which) {
                                                Intent intent = new Intent();
                                                intent.putExtra("enery", eneryForReturn);
                                                intent.putExtra("credit", creditForReturn);
                                                intent.putExtra("time", timeForReturn);
                                                setResult(RESULT_OK, intent);

                                                finish();
                                            }
                                        });
                                        errorDialog.show();
                                    } else {
                                        /*数据库数据更新*/
                                        int i = databaseManage.updateCHCurrentEnergy(CHCurrentEnergy - eachClassEnergy);
                                        i = databaseManage.updateCHCredit(CHCredit + eachClassCredit);
                                        i = databaseManage.updateCHCurrentTime(baseTime + (10 * 60));

                                        eneryForReturn = databaseManage.queryCHCurrentEnergy();
                                        creditForReturn = databaseManage.queryCHCredit();
                                        timeForReturn = databaseManage.queryCHCurrentTime();
                                        Intent intent = new Intent();
                                        intent.putExtra("enery", eneryForReturn);
                                        intent.putExtra("credit", creditForReturn);
                                        intent.putExtra("time", timeForReturn);
                                        setResult(RESULT_OK, intent);

                                        Toast.makeText(Lesson_View.this, "课程已完成~", Toast.LENGTH_SHORT).show();
                                    }
                                } });
                            dialog.show();
                        }
                    }
                }
            }
        });

        /*第二节课点击事件*/
        final Button second_lesson = findViewById(R.id.second_lesson);
        second_lesson.setText(intent.getStringExtra("second_lesson"));
        second_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!second_lesson.getText().equals("")) {
                    currentTime = baseTime + (10 * 60) + 20;
                    if (currentTime < databaseManage.queryCHCurrentTime()) {
                        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Lesson_View.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("不能选择当前时间之前的课程");
                        dialog.setCancelable(false);
                        dialog.setNegativeButton("", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.show();
                    } else {
                        CName = second_lesson.getText().toString();
                        eachClassCredit = databaseManage.queryCNameCEachClassCredit(CName);
                        eachClassEnergy = databaseManage.queryCNameCEachClassEnergy(CName);
                        //特殊判断C语言课程
                        if (CName.equals("高级语言程序设计")) {
                            CHCurrentEnergy = databaseManage.queryCHCurrentEnergy();
                            CHCredit = databaseManage.queryCHCredit();
                            int newEnergy = CHCurrentEnergy - eachClassEnergy;

                            /*当前活力值不足以进行课程*/
                            if (newEnergy < 0) {
                                android.app.AlertDialog.Builder errorDialog = new android.app.AlertDialog.Builder(Lesson_View.this);
                                errorDialog.setTitle("提示");
                                errorDialog.setMessage("活力值不足，不能进行该课程！");
                                errorDialog.setCancelable(false);
                                errorDialog.setNegativeButton("", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                errorDialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog,int which) {
                                        Intent intent = new Intent();
                                        intent.putExtra("enery", eneryForReturn);
                                        intent.putExtra("credit", creditForReturn);
                                        intent.putExtra("time", timeForReturn);
                                        setResult(RESULT_OK, intent);

                                        finish();
                                    }
                                });
                                errorDialog.show();
                            } else {
                                int cnt = databaseManage.queryCNameAttendClassNumber(CName);
                                int CHid = getCHid(databaseManage.queryCHCurrentWeek(), week, 2);

                                int flg = databaseManage.updateCHCidCHCIsAttendClass(CHid, true);
                                if (flg != 1) {
                                    Toast.makeText(Lesson_View.this, "调出课程失败！", Toast.LENGTH_SHORT).show();
                                }

                                switch (cnt) {
                                    case 0 :
                                        Intent intent1 = new Intent(Lesson_View.this, Classroom_Program_C_One.class);
                                        startActivity(intent1);
                                        break;
                                    case 1 :
                                        Intent intent2 = new Intent(Lesson_View.this, Classroom_Program_C_Two.class);
                                        startActivity(intent2);
                                        break;
                                    case 2 :
                                        Intent intent3 = new Intent(Lesson_View.this, Classroom_Program_C_Three.class);
                                        startActivity(intent3);
                                        break;
                                    case 3:
                                        break;
                                }

                                int i = databaseManage.updateCHCurrentEnergy(CHCurrentEnergy - eachClassEnergy);
                                i = databaseManage.updateCHCredit(CHCredit + eachClassCredit);
                                i = databaseManage.updateCHCurrentTime(baseTime + (12 * 60));

                                eneryForReturn = databaseManage.queryCHCurrentEnergy();
                                creditForReturn = databaseManage.queryCHCredit();
                                timeForReturn = databaseManage.queryCHCurrentTime();
                                Intent intent = new Intent();
                                intent.putExtra("enery", eneryForReturn);
                                intent.putExtra("credit", creditForReturn);
                                intent.putExtra("time", timeForReturn);
                                setResult(RESULT_OK, intent);
                            }

                        } else {
                            android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Lesson_View.this);
                            dialog.setTitle("提示");
                            dialog.setMessage("消耗活力值：" + eachClassEnergy + "\n"
                                    + "获得学分：" + df.format(eachClassCredit) );
                            dialog.setCancelable(false);
                            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog,int which) {} });
                            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog,int which) {
                                    CHCurrentEnergy = databaseManage.queryCHCurrentEnergy();
                                    CHCredit = databaseManage.queryCHCredit();
                                    int newEnergy = CHCurrentEnergy - eachClassEnergy;

                                    /*当前活力值不足以进行课程*/
                                    if (newEnergy < 0) {
                                        android.app.AlertDialog.Builder errorDialog = new android.app.AlertDialog.Builder(Lesson_View.this);
                                        errorDialog.setTitle("提示");
                                        errorDialog.setMessage("活力值不足，不能进行该课程！");
                                        errorDialog.setCancelable(false);
                                        errorDialog.setNegativeButton("", new DialogInterface.OnClickListener(){
                                            public void onClick(DialogInterface dialog,int which) {} });
                                        errorDialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                                            public void onClick(DialogInterface dialog,int which) {
                                                Intent intent = new Intent();
                                                intent.putExtra("enery", eneryForReturn);
                                                intent.putExtra("credit", creditForReturn);
                                                intent.putExtra("time", timeForReturn);
                                                setResult(RESULT_OK, intent);

                                                finish();
                                            }
                                        });
                                        errorDialog.show();
                                    } else {
                                        /*数据库数据更新*/
                                        int i = databaseManage.updateCHCurrentEnergy(CHCurrentEnergy - eachClassEnergy);
                                        i = databaseManage.updateCHCredit(CHCredit + eachClassCredit);
                                        i = databaseManage.updateCHCurrentTime(baseTime + (12 * 60));

                                        eneryForReturn = databaseManage.queryCHCurrentEnergy();
                                        creditForReturn = databaseManage.queryCHCredit();
                                        timeForReturn = databaseManage.queryCHCurrentTime();
                                        Intent intent = new Intent();
                                        intent.putExtra("enery", eneryForReturn);
                                        intent.putExtra("credit", creditForReturn);
                                        intent.putExtra("time", timeForReturn);
                                        setResult(RESULT_OK, intent);

                                        Toast.makeText(Lesson_View.this, "课程已完成~", Toast.LENGTH_SHORT).show();
                                    }
                                } });
                            dialog.show();
                        }
                    }
                }
            }
        });

        /*第三节课点击事件*/
        final Button third_lesson = findViewById(R.id.third_lesson);
        third_lesson.setText(intent.getStringExtra("third_lesson"));
        third_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!third_lesson.getText().equals("")) {
                    currentTime = baseTime + (14 * 60);
                    if (currentTime < databaseManage.queryCHCurrentTime()) {
                        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Lesson_View.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("不能选择当前时间之前的课程");
                        dialog.setCancelable(false);
                        dialog.setNegativeButton("", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.show();
                    } else {
                        CName = third_lesson.getText().toString();
                        eachClassCredit = databaseManage.queryCNameCEachClassCredit(CName);
                        eachClassEnergy = databaseManage.queryCNameCEachClassEnergy(CName);
                        //特殊判断C语言课程
                        if (CName.equals("高级语言程序设计")) {
                            CHCurrentEnergy = databaseManage.queryCHCurrentEnergy();
                            CHCredit = databaseManage.queryCHCredit();
                            int newEnergy = CHCurrentEnergy - eachClassEnergy;

                            /*当前活力值不足以进行课程*/
                            if (newEnergy < 0) {
                                android.app.AlertDialog.Builder errorDialog = new android.app.AlertDialog.Builder(Lesson_View.this);
                                errorDialog.setTitle("提示");
                                errorDialog.setMessage("活力值不足，不能进行该课程！");
                                errorDialog.setCancelable(false);
                                errorDialog.setNegativeButton("", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                errorDialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog,int which) {
                                        Intent intent = new Intent();
                                        intent.putExtra("enery", eneryForReturn);
                                        intent.putExtra("credit", creditForReturn);
                                        intent.putExtra("time", timeForReturn);
                                        setResult(RESULT_OK, intent);

                                        finish();
                                    }
                                });
                                errorDialog.show();
                            } else {
                                int cnt = databaseManage.queryCNameAttendClassNumber(CName);
                                int CHid = getCHid(databaseManage.queryCHCurrentWeek(), week, 3);

                                int flg = databaseManage.updateCHCidCHCIsAttendClass(CHid, true);
                                if (flg != 1) {
                                    Toast.makeText(Lesson_View.this, "调出课程失败！", Toast.LENGTH_SHORT).show();
                                }

                                switch (cnt) {
                                    case 0 :
                                        Intent intent1 = new Intent(Lesson_View.this, Classroom_Program_C_One.class);
                                        startActivity(intent1);
                                        break;
                                    case 1 :
                                        Intent intent2 = new Intent(Lesson_View.this, Classroom_Program_C_Two.class);
                                        startActivity(intent2);
                                        break;
                                    case 2 :
                                        Intent intent3 = new Intent(Lesson_View.this, Classroom_Program_C_Three.class);
                                        startActivity(intent3);
                                        break;
                                    case 3:
                                        break;
                                }

                                int i = databaseManage.updateCHCurrentEnergy(CHCurrentEnergy - eachClassEnergy);
                                i = databaseManage.updateCHCredit(CHCredit + eachClassCredit);
                                i = databaseManage.updateCHCurrentTime(baseTime + (15 * 60) + 40);

                                eneryForReturn = databaseManage.queryCHCurrentEnergy();
                                creditForReturn = databaseManage.queryCHCredit();
                                timeForReturn = databaseManage.queryCHCurrentTime();
                                Intent intent = new Intent();
                                intent.putExtra("enery", eneryForReturn);
                                intent.putExtra("credit", creditForReturn);
                                intent.putExtra("time", timeForReturn);
                                setResult(RESULT_OK, intent);
                            }

                        } else {
                            android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Lesson_View.this);
                            dialog.setTitle("提示");
                            dialog.setMessage("消耗活力值：" + eachClassEnergy + "\n"
                                    + "获得学分：" + df.format(eachClassCredit) );
                            dialog.setCancelable(false);
                            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog,int which) {} });
                            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog,int which) {
                                    CHCurrentEnergy = databaseManage.queryCHCurrentEnergy();
                                    CHCredit = databaseManage.queryCHCredit();
                                    int newEnergy = CHCurrentEnergy - eachClassEnergy;

                                    /*当前活力值不足以进行课程*/
                                    if (newEnergy < 0) {
                                        android.app.AlertDialog.Builder errorDialog = new android.app.AlertDialog.Builder(Lesson_View.this);
                                        errorDialog.setTitle("提示");
                                        errorDialog.setMessage("活力值不足，不能进行该课程！");
                                        errorDialog.setCancelable(false);
                                        errorDialog.setNegativeButton("", new DialogInterface.OnClickListener(){
                                            public void onClick(DialogInterface dialog,int which) {} });
                                        errorDialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                                            public void onClick(DialogInterface dialog,int which) {
                                                Intent intent = new Intent();
                                                intent.putExtra("enery", eneryForReturn);
                                                intent.putExtra("credit", creditForReturn);
                                                intent.putExtra("time", timeForReturn);
                                                setResult(RESULT_OK, intent);

                                                finish();
                                            }
                                        });
                                        errorDialog.show();
                                    } else {
                                        /*数据库数据更新*/
                                        int i = databaseManage.updateCHCurrentEnergy(CHCurrentEnergy - eachClassEnergy);
                                        i = databaseManage.updateCHCredit(CHCredit + eachClassCredit);
                                        i = databaseManage.updateCHCurrentTime(baseTime + (15 * 60) + 40);

                                        eneryForReturn = databaseManage.queryCHCurrentEnergy();
                                        creditForReturn = databaseManage.queryCHCredit();
                                        timeForReturn = databaseManage.queryCHCurrentTime();
                                        Intent intent = new Intent();
                                        intent.putExtra("enery", eneryForReturn);
                                        intent.putExtra("credit", creditForReturn);
                                        intent.putExtra("time", timeForReturn);
                                        setResult(RESULT_OK, intent);

                                        Toast.makeText(Lesson_View.this, "课程已完成~", Toast.LENGTH_SHORT).show();
                                    }
                                } });
                            dialog.show();
                        }
                    }
                }
            }
        });

        /*第四节课点击事件*/
        final Button forth_lesson = findViewById(R.id.forth_lesson);
        forth_lesson.setText(intent.getStringExtra("forth_lesson"));
        forth_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!forth_lesson.getText().equals("")) {
                    currentTime = baseTime + (15 * 60) + 50;
                    if (currentTime < databaseManage.queryCHCurrentTime()) {
                        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Lesson_View.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("不能选择当前时间之前的课程");
                        dialog.setCancelable(false);
                        dialog.setNegativeButton("", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.show();
                    } else {
                        CName = forth_lesson.getText().toString();
                        eachClassCredit = databaseManage.queryCNameCEachClassCredit(CName);
                        eachClassEnergy = databaseManage.queryCNameCEachClassEnergy(CName);
                        //特殊判断C语言课程
                        if (CName.equals("高级语言程序设计")) {
                            CHCurrentEnergy = databaseManage.queryCHCurrentEnergy();
                            CHCredit = databaseManage.queryCHCredit();
                            int newEnergy = CHCurrentEnergy - eachClassEnergy;

                            /*当前活力值不足以进行课程*/
                            if (newEnergy < 0) {
                                android.app.AlertDialog.Builder errorDialog = new android.app.AlertDialog.Builder(Lesson_View.this);
                                errorDialog.setTitle("提示");
                                errorDialog.setMessage("活力值不足，不能进行该课程！");
                                errorDialog.setCancelable(false);
                                errorDialog.setNegativeButton("", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                errorDialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog,int which) {
                                        Intent intent = new Intent();
                                        intent.putExtra("enery", eneryForReturn);
                                        intent.putExtra("credit", creditForReturn);
                                        intent.putExtra("time", timeForReturn);
                                        setResult(RESULT_OK, intent);

                                        finish();
                                    }
                                });
                                errorDialog.show();
                            } else {
                                int cnt = databaseManage.queryCNameAttendClassNumber(CName);
                                int CHid = getCHid(databaseManage.queryCHCurrentWeek(), week, 4);

                                int flg = databaseManage.updateCHCidCHCIsAttendClass(CHid, true);
                                if (flg != 1) {
                                    Toast.makeText(Lesson_View.this, "调出课程失败！", Toast.LENGTH_SHORT).show();
                                }

                                switch (cnt) {
                                    case 0 :
                                        Intent intent1 = new Intent(Lesson_View.this, Classroom_Program_C_One.class);
                                        startActivity(intent1);
                                        break;
                                    case 1 :
                                        Intent intent2 = new Intent(Lesson_View.this, Classroom_Program_C_Two.class);
                                        startActivity(intent2);
                                        break;
                                    case 2 :
                                        Intent intent3 = new Intent(Lesson_View.this, Classroom_Program_C_Three.class);
                                        startActivity(intent3);
                                        break;
                                    case 3:
                                        break;
                                }

                                int i = databaseManage.updateCHCurrentEnergy(CHCurrentEnergy - eachClassEnergy);
                                i = databaseManage.updateCHCredit(CHCredit + eachClassCredit);
                                i = databaseManage.updateCHCurrentTime(baseTime + (17 * 60) + 30);

                                eneryForReturn = databaseManage.queryCHCurrentEnergy();
                                creditForReturn = databaseManage.queryCHCredit();
                                timeForReturn = databaseManage.queryCHCurrentTime();
                                Intent intent = new Intent();
                                intent.putExtra("enery", eneryForReturn);
                                intent.putExtra("credit", creditForReturn);
                                intent.putExtra("time", timeForReturn);
                                setResult(RESULT_OK, intent);
                            }

                        } else {
                            android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Lesson_View.this);
                            dialog.setTitle("提示");
                            dialog.setMessage("消耗活力值：" + eachClassEnergy + "\n"
                                    + "获得学分：" + df.format(eachClassCredit) );
                            dialog.setCancelable(false);
                            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog,int which) {} });
                            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog,int which) {
                                    CHCurrentEnergy = databaseManage.queryCHCurrentEnergy();
                                    CHCredit = databaseManage.queryCHCredit();
                                    int newEnergy = CHCurrentEnergy - eachClassEnergy;

                                    /*当前活力值不足以进行课程*/
                                    if (newEnergy < 0) {
                                        android.app.AlertDialog.Builder errorDialog = new android.app.AlertDialog.Builder(Lesson_View.this);
                                        errorDialog.setTitle("提示");
                                        errorDialog.setMessage("活力值不足，不能进行该课程！");
                                        errorDialog.setCancelable(false);
                                        errorDialog.setNegativeButton("", new DialogInterface.OnClickListener(){
                                            public void onClick(DialogInterface dialog,int which) {} });
                                        errorDialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                                            public void onClick(DialogInterface dialog,int which) {
                                                Intent intent = new Intent();
                                                intent.putExtra("enery", eneryForReturn);
                                                intent.putExtra("credit", creditForReturn);
                                                intent.putExtra("time", timeForReturn);
                                                setResult(RESULT_OK, intent);

                                                finish();
                                            }
                                        });
                                        errorDialog.show();
                                    } else {
                                        /*数据库数据更新*/
                                        int i = databaseManage.updateCHCurrentEnergy(CHCurrentEnergy - eachClassEnergy);
                                        i = databaseManage.updateCHCredit(CHCredit + eachClassCredit);
                                        i = databaseManage.updateCHCurrentTime(baseTime + (17 * 60) + 30);

                                        eneryForReturn = databaseManage.queryCHCurrentEnergy();
                                        creditForReturn = databaseManage.queryCHCredit();
                                        timeForReturn = databaseManage.queryCHCurrentTime();
                                        Intent intent = new Intent();
                                        intent.putExtra("enery", eneryForReturn);
                                        intent.putExtra("credit", creditForReturn);
                                        intent.putExtra("time", timeForReturn);
                                        setResult(RESULT_OK, intent);

                                        Toast.makeText(Lesson_View.this, "课程已完成~", Toast.LENGTH_SHORT).show();
                                    }

                                } });
                            dialog.show();
                        }
                    }
                }
            }
        });

        /*第五节课点击事件*/
        final Button fifth_lesson = findViewById(R.id.fifth_lesson);
        fifth_lesson.setText(intent.getStringExtra("fifth_lesson"));
        fifth_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });
    }
}
