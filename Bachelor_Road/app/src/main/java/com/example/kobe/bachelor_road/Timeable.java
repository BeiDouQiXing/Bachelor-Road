package com.example.kobe.bachelor_road;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Timeable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeable);

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
                startActivity(intent);
            }
        });
    }
}
