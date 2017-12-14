package com.example.kobe.bachelor_road;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class FailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_ending_two);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
