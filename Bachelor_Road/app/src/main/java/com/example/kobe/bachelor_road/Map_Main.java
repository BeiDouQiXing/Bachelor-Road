package com.example.kobe.bachelor_road;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kobe on 2017/11/6.
 */

public class Map_Main extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button_departmentButton = findViewById(R.id.main_department_button); //进入素拓，完成部门工作任务
        button_departmentButton.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 Intent intent = new Intent(Map_Main.this,Activity.class);
                                                 startActivity(intent);
                                             }
                                         }
        );
        Button button_building = findViewById(R.id.main_teaching_building_button);
        button_building.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map_Main.this, Timeable.class);
                startActivity(intent);
            }
        });

        Button main_dormitory_button = findViewById(R.id.main_dormitory_button);
        main_dormitory_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map_Main.this, Dorm.class);
                startActivity(intent);
            }
        });





    }
}
