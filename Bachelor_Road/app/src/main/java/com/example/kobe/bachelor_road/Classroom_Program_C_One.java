package com.example.kobe.bachelor_road;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Classroom_Program_C_One extends AppCompatActivity {

    private  Dialog dialog = new Dialog(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classroom_c_one);


        Button class_run_out = findViewById(R.id.class_one_run_out);
        class_run_out.setOnClickListener(new  View.OnClickListener() {
            @Override

            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.test_two).setOnClickListener((View.OnClickListener) this);

    }

    public void onClick(View v) {
        switch(v.getId()){
          /*  case R.id.test_two:
                //创建对话框
                dialog= new Dialog(this);
                View view_fill_blank = LayoutInflater.from(this).inflate(R.layout.dialog_view,null);
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
                //显示对话框
                dialog.show();
                break;
            case R.id.return_blackboard:
                dialog.dismiss();
                break;
            case R.id.submit:
                dialog.dismiss();
                //这里实现业务逻辑
                break;
          */
            case R.id.test_one:
                dialog = new Dialog(this);
                View view_judge= LayoutInflater.from(this).inflate(R.layout.dialog_judge,null);
                view_judge.findViewById(R.id.yes).setOnClickListener((View.OnClickListener) this);
                view_judge.findViewById(R.id.no).setOnClickListener((View.OnClickListener) this);
                dialog.setContentView(view_judge);
                WindowManager.LayoutParams params_judge = dialog.getWindow().getAttributes();
                params_judge.width = 1500;
                dialog.getWindow().setAttributes(params_judge);
                //show之前设置返回键无效，触摸屏无效
                dialog.setCancelable(false);

               TextView textView = findViewById(R.id.question_judge);
               textView.setText(R.string.question_one);
                //显示对话框
                dialog.show();
                break;
            case R.id.yes:
                Toast.makeText(this,"回答正确",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                finish();
                break;
            case R.id.no:
                dialog.dismiss();
                //这里实现业务逻辑
                break;
        }
    }
}
