package com.example.kobe.bachelor_road;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kobe on 2017/11/15.
 */


public class Classroom_Program_C_Three extends AppCompatActivity implements View.OnClickListener {

    private ImageView image =findViewById(R.id.c_program3);
    private int imgTimes = 0;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classroom_c_three);

        findViewById(R.id.test_three).setOnClickListener(this);
        Button class_run_out = findViewById(R.id.class_three_run_out);
        class_run_out.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                finish();
            }
        });

    }
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.test_three:
                //创建对话框
                dialog= new Dialog(this);
                View view_fill_blank = LayoutInflater.from(this).inflate(R.layout.dialog_view_two,null);
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
                break;
            case R.id.left_button_three:
                if(imgTimes == 0) {
                    Toast.makeText(Classroom_Program_C_Three.this, "已经是最前页了", Toast.LENGTH_SHORT).show();
                    return;
                } else if (imgTimes == 1){
                    imgTimes--;
                    image.setImageResource(R.drawable.c_program3_1);
                    Toast.makeText(Classroom_Program_C_Three.this, "切换成功", Toast.LENGTH_SHORT).show();
                } else if (imgTimes == 2){
                    imgTimes--;
                    image.setImageResource(R.drawable.c_program3_2);
                    Toast.makeText(Classroom_Program_C_Three.this, "切换成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.right_button_three:
                 if(imgTimes == 2){
                    imgTimes++;
                    Toast.makeText(Classroom_Program_C_Three.this, "已经是最后页了", Toast.LENGTH_SHORT).show();
                    return;
                } else if(imgTimes == 1){
                    imgTimes++;
                    image.setImageResource(R.drawable.c_program3_3);
                    Toast.makeText(Classroom_Program_C_Three.this, "切换成功", Toast.LENGTH_SHORT).show();
                } else if(imgTimes == 0){
                    imgTimes++;
                    image.setImageResource(R.drawable.c_program3_2);
                    Toast.makeText(Classroom_Program_C_Three.this, "切换成功", Toast.LENGTH_SHORT).show();
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