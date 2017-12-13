package com.example.kobe.bachelor_road;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Leading extends AppCompatActivity {

    private int pictureNumber = 0;

    @Override
    public void onBackPressed() {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Leading.this);
        dialog.setTitle("提示");
        dialog.setMessage("点击确定开始选择部门\n选择部门后直接开始第二周");
        dialog.setCancelable(false);
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which) {} });
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which) {
                Intent intent1 = new Intent(Leading.this, Map_Main.class);
                startActivity(intent1);
                Intent intent2 = new Intent(Leading.this, Add_Department.class);
                startActivity(intent2);
                finish();
            } });
        dialog.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leading);

        /*退出按钮点击事件*/
        Button leading_out = findViewById(R.id.leading_out);
        leading_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Leading.this);
                dialog.setTitle("提示");
                dialog.setMessage("点击确定开始选择部门\n选择部门后直接开始第二周");
                dialog.setCancelable(false);
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which) {} });
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which) {
                        Intent intent1 = new Intent(Leading.this, Map_Main.class);
                        startActivity(intent1);
                        Intent intent2 = new Intent(Leading.this, Add_Department.class);
                        startActivity(intent2);
                        finish();
                    } });
                dialog.show();
            }
        });

        /*下一张按钮点击事件*/
        Button leading_next = findViewById(R.id.leading_next);
        leading_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView leading_image = findViewById(R.id.leading_image);
                switch (pictureNumber) {
                    case 0:
                        pictureNumber++;
                        leading_image.setBackgroundResource(R.drawable.leading_2);
                        break;
                    case 1:
                        pictureNumber++;
                        leading_image.setBackgroundResource(R.drawable.leading_3);
                        break;
                    case 2:
                        pictureNumber++;
                        leading_image.setBackgroundResource(R.drawable.leading_4);
                        break;
                    case 3:
                        pictureNumber++;
                        leading_image.setBackgroundResource(R.drawable.leading_5);
                        break;
                    case 4:
                        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Leading.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("点击确定开始选择部门\n选择部门后直接开始第二周");
                        dialog.setCancelable(false);
                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {} });
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {
                                Intent intent = new Intent(Leading.this, Add_Department.class);
                                startActivity(intent);
                                finish();
                            } });
                        dialog.show();
                        break;
                    default:
                        break;
                }
            }
        });

        /*上一张按钮点击事件*/
        Button leading_back = findViewById(R.id.leading_back);
        leading_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView leading_image = findViewById(R.id.leading_image);
                switch (pictureNumber) {
                    case 0:
                        break;
                    case 1:
                        pictureNumber--;
                        leading_image.setBackgroundResource(R.drawable.leading_1);
                        break;
                    case 2:
                        pictureNumber--;
                        leading_image.setBackgroundResource(R.drawable.leading_2);
                        break;
                    case 3:
                        pictureNumber--;
                        leading_image.setBackgroundResource(R.drawable.leading_3);
                        break;
                    case 4:
                        pictureNumber--;
                        leading_image.setBackgroundResource(R.drawable.leading_4);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
