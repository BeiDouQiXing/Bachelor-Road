package com.example.kobe.bachelor_road;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Lesson_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_view);

        Intent intent = getIntent();
        String data = intent.getStringExtra("week");

        TextView lesson_week = findViewById(R.id.lesson_week);
        lesson_week.setText(data);

        ImageView lesson_view_run_out = findViewById(R.id.lesson_view_run_out);
        lesson_view_run_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Button first_lesson = findViewById(R.id.first_lesson);
        first_lesson.setText(intent.getStringExtra("first_lesson"));
        first_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!first_lesson.getText().equals("1~2:")) {
                    Intent intent = new Intent(Lesson_View.this, Classroom.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        final Button second_lesson = findViewById(R.id.second_lesson);
        second_lesson.setText(intent.getStringExtra("second_lesson"));
        second_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!second_lesson.getText().equals("3~4:")) {
                    Intent intent = new Intent(Lesson_View.this, Classroom.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        final Button third_lesson = findViewById(R.id.third_lesson);
        third_lesson.setText(intent.getStringExtra("third_lesson"));
        third_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!third_lesson.getText().equals("5~6:")) {
                    Intent intent = new Intent(Lesson_View.this, Classroom.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        final Button forth_lesson = findViewById(R.id.forth_lesson);
        forth_lesson.setText(intent.getStringExtra("forth_lesson"));
        forth_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!forth_lesson.getText().equals("7~8:")) {
                    Intent intent = new Intent(Lesson_View.this, Classroom.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        final Button fifth_lesson = findViewById(R.id.fifth_lesson);
        fifth_lesson.setText("晚自习");
        fifth_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fifth_lesson.getText().equals("9~10:")) {
                    Intent intent = new Intent(Lesson_View.this, Classroom.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
