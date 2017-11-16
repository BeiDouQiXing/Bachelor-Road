package com.example.kobe.bachelor_road;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.Instant;

public class Login extends AppCompatActivity {

    private DatabaseManage databaseManage;
    private boolean isRead = false;
    private  int boyOrGirl = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseManage = new DatabaseManage(this);

        if(databaseManage.queryCHIsFirstLogin().equals("false")) {
            Intent intent = new Intent(Login.this, Map_Main.class);
            Toast.makeText(Login.this,"欢迎回来！",
                    Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }


        //选择男女头像
        Button buttonHeadBoy = (Button) findViewById(R.id.boy);
        Button buttonHeadGirl = (Button) findViewById(R.id.girl);
        buttonHeadBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boyOrGirl = 1;
                Toast.makeText(Login.this,"您选择为男生",
                        Toast.LENGTH_SHORT).show();
            }
        });
        buttonHeadGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boyOrGirl = 2;
                Toast.makeText(Login.this,"您选择为女生",
                        Toast.LENGTH_SHORT).show();
            }
        });


        //获取登录界面的学生注册信息,点击确定按钮
        Button buttonConfirm = (Button)findViewById(R.id.queren);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                EditText editTextStuName = (EditText)findViewById(R.id.name);
                EditText editTextStuNum = (EditText)findViewById(R.id.student_number);
                EditText editTextStuClass = (EditText)findViewById(R.id.student_number);

                if(editTextStuName.length() == 0||editTextStuNum.length() == 0){

                    Toast.makeText(Login.this,"请输入正确的姓名或学号",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                String stuName = editTextStuName.getText().toString();
                String stuNum = editTextStuNum.getText().toString();
                int stuClass = Integer.valueOf(editTextStuClass.getText().toString());
                //判断是否合法

               if(isOk(stuName, stuNum) == false){

                    Toast.makeText(Login.this,"请输入正确的姓名或学号",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else if(boyOrGirl == 0){
                    Toast.makeText(Login.this, "请选择正确的头像",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else if(isOk(stuName, stuNum)==true) {
                    //（待写入）存入数据库
                    Character character = new Character(1,stuName,stuNum,stuClass,boyOrGirl,100,100,0,0,480,1);
                    long results = databaseManage.insertCharacter(character);


                    Intent intent = new Intent(Login.this, Map_Main.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(Login.this,"注册成功",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isOk(String name, String num){
        if(name == null||num == null|| name.equals("")==true||num.equals("")==true) {
            return false;
        } else {
            return true;
        }
    }

}

