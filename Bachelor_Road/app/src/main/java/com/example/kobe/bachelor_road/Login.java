package com.example.kobe.bachelor_road;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private DatabaseManage databaseManage;
    private  String boyOrGirl = "";
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
        Button buttonHeadBoy = findViewById(R.id.boy);
        Button buttonHeadGirl = findViewById(R.id.girl);
        buttonHeadBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boyOrGirl = "male";
                Toast.makeText(Login.this,"您选择为男生",
                        Toast.LENGTH_SHORT).show();
            }
        });
        buttonHeadGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boyOrGirl = "female";
                Toast.makeText(Login.this,"您选择为女生",
                        Toast.LENGTH_SHORT).show();
            }
        });


        //获取登录界面的学生注册信息,点击确定按钮
        Button buttonConfirm = findViewById(R.id.queren);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                EditText editTextStuName = findViewById(R.id.name);
                EditText editTextStuNum = findViewById(R.id.student_number);
                EditText editTextStuClass = findViewById(R.id.student_number);

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
                } else if(boyOrGirl == ""){
                    Toast.makeText(Login.this, "请选择正确的头像",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else if(isOk(stuName, stuNum)==true) {
                    //（待写入）存入数据库
                    Character character = new Character(1,stuName,stuNum,stuClass,boyOrGirl,100,100,0,0,480,1,null);
                    Log.i("hujunqin",boyOrGirl);
                    databaseManage.insertCharacter(character);

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

