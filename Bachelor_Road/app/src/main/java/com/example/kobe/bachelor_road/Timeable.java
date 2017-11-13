package com.example.kobe.bachelor_road;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        //int i = databaseManage.updateCHCurrentWeek(2);
        
        final int currentWeek = databaseManage.queryCHCurrentWeek();
        characterCourses = databaseManage.queryCharacterCourse(currentWeek);

        Button timetable_run_out =findViewById(R.id.timetable_run_out);
        timetable_run_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button button_mon = findViewById(R.id.Mon);
        button_mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Mon";
                Intent intent = new Intent(Timeable.this, Lesson_View.class);
                intent.putExtra("week", data);
                intent.putExtra("first_lesson", characterCourses[0].CName);
                intent.putExtra("second_lesson", characterCourses[1].CName);
                intent.putExtra("third_lesson", characterCourses[2].CName);
                intent.putExtra("forth_lesson", characterCourses[3].CName);
                startActivity(intent);
            }
        });

        Button button_tues = findViewById(R.id.Tues);
        button_tues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Tues";
                Intent intent = new Intent(Timeable.this, Lesson_View.class);
                intent.putExtra("week", data);
                intent.putExtra("first_lesson", characterCourses[4].CName);
                intent.putExtra("second_lesson", characterCourses[5].CName);
                intent.putExtra("third_lesson", characterCourses[6].CName);
                intent.putExtra("forth_lesson", characterCourses[7].CName);
                startActivity(intent);
            }
        });

        Button button_wed = findViewById(R.id.Wed);
        button_wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Wed";
                Intent intent = new Intent(Timeable.this, Lesson_View.class);
                intent.putExtra("week", data);
                intent.putExtra("first_lesson", characterCourses[8].CName);
                intent.putExtra("second_lesson", characterCourses[9].CName);
                intent.putExtra("third_lesson", characterCourses[10].CName);
                intent.putExtra("forth_lesson", characterCourses[11].CName);
                startActivity(intent);
            }
        });

        Button button_thu = findViewById(R.id.Thu);
        button_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Thu";
                Intent intent = new Intent(Timeable.this, Lesson_View.class);
                intent.putExtra("week", data);
                intent.putExtra("first_lesson", characterCourses[12].CName);
                intent.putExtra("second_lesson", characterCourses[13].CName);
                intent.putExtra("third_lesson", characterCourses[14].CName);
                intent.putExtra("forth_lesson", characterCourses[15].CName);
                startActivity(intent);
            }
        });

        Button button_fri = findViewById(R.id.Fri);
        button_fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Fri";
                Intent intent = new Intent(Timeable.this, Lesson_View.class);
                intent.putExtra("week", data);
                intent.putExtra("first_lesson", characterCourses[16].CName);
                intent.putExtra("second_lesson", characterCourses[17].CName);
                intent.putExtra("third_lesson", characterCourses[18].CName);
                intent.putExtra("forth_lesson", characterCourses[19].CName);
                startActivity(intent);
            }
        });
    }
}
