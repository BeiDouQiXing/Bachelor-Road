package com.example.kobe.bachelor_road;

import android.app.Dialog;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kobe on 2017/11/15.
 */

public class Classroom_Program_C_Two extends AppCompatActivity implements View.OnClickListener {

    private ImageView image = findViewById(R.id.c_program2);
    private int imgTimes = 0;
private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classroom_c_two);
        findViewById(R.id.class_two_run_out).setOnClickListener(this);
        findViewById(R.id.test_two).setOnClickListener(this);




    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.class_two_run_out:
                finish();
            case R.id.test_two:
                //创建对话框
                View view_fill_blank = LayoutInflater.from(this).inflate(R.layout.dialog_view_one,null);
                //给Dialog中的子view设置事件监听
                view_fill_blank.findViewById(R.id.return_blackboard).setOnClickListener((View.OnClickListener) this);
                view_fill_blank.findViewById(R.id.submit).setOnClickListener((View.OnClickListener) this);
                dialog.setContentView(view_fill_blank);
                //自定义宽高（高度一般不用调整，在xml调整好就可以了，这里我只调整了宽度）
                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                params.width = 1500;
                dialog.getWindow().setAttributes(params);
                //show之前设置返回键无效，触摸屏无效
                dialog.setCancelable(false);
               TextView textView = (TextView)findViewById(R.id.question_submit);
                //显示对话框
                dialog.show();
                break;
            case R.id.return_blackboard:
                dialog.dismiss();
                break;
            case R.id.submit:
                TextView textViewGet = (TextView)findViewById(R.id.question_submit);
                if(textViewGet.getText().toString().equals("30")==true) {
                    Toast.makeText(this, "回答正确", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    finish();
                } else {
                    Toast.makeText(this, "再想想呗，回答不太对哦", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                //这里实现业务逻辑
                break;
             case R.id.left_button_two:
                 if(imgTimes == 0) {
                     Toast.makeText(Classroom_Program_C_Two.this, "已经是最前页了", Toast.LENGTH_SHORT).show();
                     return;
                 } else {
                     imgTimes--;
                     image.setImageResource(R.drawable.c_program2_2);
                     Toast.makeText(Classroom_Program_C_Two.this, "切换成功", Toast.LENGTH_SHORT).show();
                 }
                break;
            case R.id.right_button_two:
                if(imgTimes == 1) {
                    Toast.makeText(Classroom_Program_C_Two.this, "已经是最后页了", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    imgTimes++;
                    image.setImageResource(R.drawable.c_program2_1);
                    Toast.makeText(Classroom_Program_C_Two.this, "切换成功", Toast.LENGTH_SHORT).show();
                }
                break;



        /* case R.id.test_two:
                dialog = new Dialog(this);
                View view_judge= LayoutInflater.from(this).inflate(R.layout.dialog_judge_one,null);
                view_judge.findViewById(R.id.yes).setOnClickListener((View.OnClickListener) this);
                view_judge.findViewById(R.id.no).setOnClickListener((View.OnClickListener) this);
                dialog.setContentView(view_judge);
                WindowManager.LayoutParams params_judge = dialog.getWindow().getAttributes();
                params_judge.width = 1500;
                dialog.getWindow().setAttributes(params_judge);
                //show之前设置返回键无效，触摸屏无效
                dialog.setCancelable(false);
                //显示对话框
                dialog.show();
                break;
            case R.id.yes:
                dialog.dismiss();
                break;
            case R.id.no:
                dialog.dismiss();
                //这里实现业务逻辑
                break;
            case R.id.class_one_run_out:
                finish();*/
        }
    }
}