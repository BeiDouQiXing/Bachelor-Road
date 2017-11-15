package com.example.kobe.bachelor_road;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Timeable extends AppCompatActivity {

    private DatabaseManage databaseManage;
    private CharacterCourse[] characterCourses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeable);

        databaseManage = new DatabaseManage(this);

        /*新生周数据尚未录入，修改当前周为第二周进行测试*/
        final int currentWeek = databaseManage.queryCHCurrentWeek();
        if(currentWeek == 1) {
            android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Timeable.this);
            dialog.setTitle("提示");
            dialog.setMessage("如果还未选择部门请慎重考虑");
            dialog.setCancelable(false);
            dialog.setNegativeButton("再去看看咯", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog,int which) {}});

               dialog.setPositiveButton("我打算从第二周开始！", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which){
                                Toast.makeText(Timeable.this,"新生周已跳过~",Toast.LENGTH_SHORT).show();
                                int i = databaseManage.updateCHCurrentWeek(2);
                            }
                        });
                dialog.show();
        }

        /*获取当前周数及相应课程*/
        characterCourses = databaseManage.queryCharacterCourse(currentWeek);

        /*退出按钮点击事件*/
        Button timetable_run_out =findViewById(R.id.timetable_run_out);
        timetable_run_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*周一按钮点击事件*/
        Button button_mon = findViewById(R.id.Mon);
        button_mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Mon";
                Intent intent = new Intent(Timeable.this, Lesson_View.class);
                intent.putExtra("week", data);

                /*传给Lesson_View当日课程信息*/
                String first_lesson = characterCourses[0].CName;
                if (first_lesson.equals("")) {
                    intent.putExtra("first_lesson", "");
                } else {
                    intent.putExtra("first_lesson", first_lesson);
                }

                String second_lesson = characterCourses[1].CName;
                if (second_lesson.equals("")) {
                    intent.putExtra("second_lesson", "");
                } else {
                    intent.putExtra("second_lesson", second_lesson);
                }

                String third_lesson = characterCourses[2].CName;
                if (third_lesson.equals("")) {
                    intent.putExtra("third_lesson", "");
                } else {
                    intent.putExtra("third_lesson", third_lesson);
                }

                String forth_lesson = characterCourses[3].CName;
                if (forth_lesson.equals("")) {
                    intent.putExtra("forth_lesson", "");
                } else {
                    intent.putExtra("forth_lesson", forth_lesson);
                }

                String fifth_lesson = characterCourses[4].CName;
                if (fifth_lesson.equals("")) {
                    intent.putExtra("fifth_lesson", "");
                } else {
                    intent.putExtra("fifth_lesson", fifth_lesson);
                }

                startActivity(intent);
            }
        });

        /*周二按钮点击事件*/
        Button button_tues = findViewById(R.id.Tues);
        button_tues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Tues";
                Intent intent = new Intent(Timeable.this, Lesson_View.class);
                intent.putExtra("week", data);

                /*传给Lesson_View当日课程信息*/
                String first_lesson = characterCourses[5].CName;
                if (first_lesson.equals("")) {
                    intent.putExtra("first_lesson", "");
                } else {
                    intent.putExtra("first_lesson", first_lesson);
                }

                String second_lesson = characterCourses[6].CName;
                if (second_lesson.equals("")) {
                    intent.putExtra("second_lesson", "");
                } else {
                    intent.putExtra("second_lesson", second_lesson);
                }

                String third_lesson = characterCourses[7].CName;
                if (third_lesson.equals("")) {
                    intent.putExtra("third_lesson", "");
                } else {
                    intent.putExtra("third_lesson", third_lesson);
                }

                String forth_lesson = characterCourses[8].CName;
                if (forth_lesson.equals("")) {
                    intent.putExtra("forth_lesson", "");
                } else {
                    intent.putExtra("forth_lesson", forth_lesson);
                }

                String fifth_lesson = characterCourses[9].CName;
                if (fifth_lesson.equals("")) {
                    intent.putExtra("fifth_lesson", "");
                } else {
                    intent.putExtra("fifth_lesson", fifth_lesson);
                }

                startActivity(intent);
            }
        });

        /*周三按钮点击事件*/
        Button button_wed = findViewById(R.id.Wed);
        button_wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Wed";
                Intent intent = new Intent(Timeable.this, Lesson_View.class);
                intent.putExtra("week", data);

                /*传给Lesson_View当日课程信息*/
                String first_lesson = characterCourses[10].CName;
                if (first_lesson.equals("")) {
                    intent.putExtra("first_lesson", "");
                } else {
                    intent.putExtra("first_lesson", first_lesson);
                }

                String second_lesson = characterCourses[11].CName;
                if (second_lesson.equals("")) {
                    intent.putExtra("second_lesson", "");
                } else {
                    intent.putExtra("second_lesson", second_lesson);
                }

                String third_lesson = characterCourses[12].CName;
                if (third_lesson.equals("")) {
                    intent.putExtra("third_lesson", "");
                } else {
                    intent.putExtra("third_lesson", third_lesson);
                }

                String forth_lesson = characterCourses[13].CName;
                if (forth_lesson.equals("")) {
                    intent.putExtra("forth_lesson", "");
                } else {
                    intent.putExtra("forth_lesson", forth_lesson);
                }

                String fifth_lesson = characterCourses[14].CName;
                if (fifth_lesson.equals("")) {
                    intent.putExtra("fifth_lesson", "");
                } else {
                    intent.putExtra("fifth_lesson", fifth_lesson);
                }

                startActivity(intent);
            }
        });

        /*周四按钮点击事件*/
        Button button_thu = findViewById(R.id.Thu);
        button_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Thu";
                Intent intent = new Intent(Timeable.this, Lesson_View.class);
                intent.putExtra("week", data);

                /*传给Lesson_View当日课程信息*/
                String first_lesson = characterCourses[15].CName;
                if (first_lesson.equals("")) {
                    intent.putExtra("first_lesson", "");
                } else {
                    intent.putExtra("first_lesson", first_lesson);
                }

                String second_lesson = characterCourses[16].CName;
                if (second_lesson.equals("")) {
                    intent.putExtra("second_lesson", "");
                } else {
                    intent.putExtra("second_lesson", second_lesson);
                }

                String third_lesson = characterCourses[17].CName;
                if (third_lesson.equals("")) {
                    intent.putExtra("third_lesson", "");
                } else {
                    intent.putExtra("third_lesson", third_lesson);
                }

                String forth_lesson = characterCourses[18].CName;
                if (forth_lesson.equals("")) {
                    intent.putExtra("forth_lesson", "");
                } else {
                    intent.putExtra("forth_lesson", forth_lesson);
                }

                String fifth_lesson = characterCourses[19].CName;
                if (fifth_lesson.equals("")) {
                    intent.putExtra("fifth_lesson", "");
                } else {
                    intent.putExtra("fifth_lesson", fifth_lesson);
                }

                startActivity(intent);
            }
        });

        /*周五按钮点击事件*/
        Button button_fri = findViewById(R.id.Fri);
        button_fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Fri";
                Intent intent = new Intent(Timeable.this, Lesson_View.class);
                intent.putExtra("week", data);

                /*传给Lesson_View当日课程信息*/
                String first_lesson = characterCourses[20].CName;
                if (first_lesson.equals("")) {
                    intent.putExtra("first_lesson", "");
                } else {
                    intent.putExtra("first_lesson", first_lesson);
                }

                String second_lesson = characterCourses[21].CName;
                if (second_lesson.equals("")) {
                    intent.putExtra("second_lesson", "");
                } else {
                    intent.putExtra("second_lesson", second_lesson);
                }

                String third_lesson = characterCourses[22].CName;
                if (third_lesson.equals("")) {
                    intent.putExtra("third_lesson", "");
                } else {
                    intent.putExtra("third_lesson", third_lesson);
                }

                String forth_lesson = characterCourses[23].CName;
                if (forth_lesson.equals("")) {
                    intent.putExtra("forth_lesson", "");
                } else {
                    intent.putExtra("forth_lesson", forth_lesson);
                }

                String fifth_lesson = characterCourses[24].CName;
                if (fifth_lesson.equals("")) {
                    intent.putExtra("fifth_lesson", "");
                } else {
                    intent.putExtra("fifth_lesson", fifth_lesson);
                }

                startActivity(intent);
            }
        });
    }
}
