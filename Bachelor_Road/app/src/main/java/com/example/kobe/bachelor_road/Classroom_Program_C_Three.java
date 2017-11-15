package com.example.kobe.bachelor_road;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by kobe on 2017/11/15.
 */


public class Classroom_Program_C_Three extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classroom_c_three);

        Button class_run_out = findViewById(R.id.class_three_run_out);
        class_run_out.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                finish();
            }
        });

    }
}