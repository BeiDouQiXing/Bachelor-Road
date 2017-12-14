package com.example.kobe.bachelor_road;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by kobe on 2017/11/6.
 */

public class Map_Main extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        databaseManage = new DatabaseManage(this);
        final int currentWeek = databaseManage.queryCHCurrentWeek();
        final int currentTime = databaseManage.queryCHCurrentTime();
        DecimalFormat df = new DecimalFormat("#0.000");

         /*背景音乐播放*/
        Intent intent = new Intent(Map_Main.this, MyService1.class);
        startService(intent);

         /*主界面人物信息显示*/
        TextView main_name = findViewById(R.id.main_name);
        main_name.setText(databaseManage.queryCHName());
        TextView main_vitality_value = findViewById(R.id.main_vitality_value);
        main_vitality_value.setText(String.valueOf(databaseManage.queryCHCurrentEnergy()));
        TextView main_study_value = findViewById(R.id.main_study_value);
        main_study_value.setText(String.valueOf(df.format(databaseManage.queryCHCredit())));
        TextView main_activity_point = findViewById(R.id.main_activity_point);
        main_activity_point.setText(String.valueOf(df.format(databaseManage.queryCHComprehensiveTest())));
        /*主界面时间信息显示*/
        TextView main_which_week = findViewById(R.id.main_which_week);
        main_which_week.setText("第" + String.valueOf(currentWeek) + "周");
        TextView main_which_noon = findViewById(R.id.main_which_noon);
        main_which_noon.setText(TimeTranslate.morningOrAfter(currentTime));
        TextView main_current_time = findViewById(R.id.main_current_time);
        main_current_time.setText(TimeTranslate.timeIntToString(currentTime));
    }

    @Override
    protected void onStop() {

        Intent intent = new Intent(Map_Main.this, MyService1.class);
        stopService(intent);
        super.onStop();
    }

    private DatabaseManage databaseManage=new DatabaseManage(this);
    private static final int CHOOSE_PHOTO = 13;
    private CircleImageView bigHead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*背景音乐播放*/
        Intent intent = new Intent(Map_Main.this, MyService1.class);
        startService(intent);


        /*主界面部门按钮点击事件*/
        Button button_departmentButton = findViewById(R.id.main_department_button); //进入素拓，完成部门工作任务
        button_departmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Map_Main.this);
                dialog.setTitle("提示");
                dialog.setMessage("只能在第一周选择一个部门\n");
                dialog.setCancelable(false);
                dialog.setNegativeButton("", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which) {} });
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which) {
                        if( databaseManage.queryCHCurrentWeek() == 1 && Add_Department.isAdmit(databaseManage) == false ) {
                            Intent intent = new Intent(Map_Main.this, Add_Department.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(Map_Main.this, Activity.class);
                            startActivity(intent);
                        }
                    } });
                dialog.show();
            }
        });

        /*主界面课程按钮点击事件*/
        Button button_building = findViewById(R.id.main_teaching_building_button);
        button_building.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(Map_Main.this);
                dialog.setTitle("提示");
                dialog.setMessage("只能选择当前时间之后的课程\n时间将在完成课程后更新");
                dialog.setCancelable(false);
                dialog.setNegativeButton("", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which) {} });
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which) {
                        Intent intent = new Intent(Map_Main.this, Timeable.class);
                        startActivity(intent);
                    } });
                dialog.show();
            }
        });
        /*主界面宿舍按钮点击事件*/
        Button main_dormitory_button = findViewById(R.id.main_dormitory_button);
        main_dormitory_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map_Main.this, Dorm.class);
                startActivity(intent);
            }
        });

        /*头像监听事件*/
        bigHead = findViewById(R.id.main_big_head);
        bigHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看权限
                if (ContextCompat.checkSelfPermission(Map_Main.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Map_Main.this, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
            }
        });

        if (databaseManage.queryCHGender().equals("male")) {
            Glide.with(this).load(R.drawable.boy)
                    .into(bigHead);
        } else if(databaseManage.queryCHGender().equals("female")){
            Glide.with(this).load(R.drawable.girl)
                    .into(bigHead);
        }else{
            getBigHead();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        databaseManage = new DatabaseManage(this);

        switch (requestCode) {
            case CHOOSE_PHOTO:
                // 判断手机系统版本号
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 手机系统在4.4及以上的才能使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        // 手机系统在4.4以下的使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }

    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                cursor.close();
            }
        }
        return path;
    }


    @Override
    // 申请用户权限
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.
                        PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    // 用户拒绝授权
                    Toast.makeText(this, "You denied the permission",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }


    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = null;
        // 如果是document类型的Uri，，则通过document id处理
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                // 解析出数字格式的id
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content:" +
                        "//downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径
            imagePath = uri.getPath();
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，使用普通方式处理
            imagePath = getImagePath(uri, null);
        }

        // 将图片以二进制形式存进数据库
        saveBigHead(imagePath);

        // 从数据库获取头像
        getBigHead();

    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);

        // 将图片以二进制形式存进数据库
        saveBigHead(imagePath);

        // 从数据库获取头像
        getBigHead();

    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        // 打开相册
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    // 将图片以二进制的形式存进数据库
    private void saveBigHead(String imagePath){
        // 图片压缩
        BitmapFactory.Options options = new BitmapFactory.Options();// 解析位图的附加条件
        options.inJustDecodeBounds = true;    // 不去解析位图，只获取位图头文件信息
        Bitmap bitmap= BitmapFactory.decodeFile(imagePath,options);
        bigHead.setImageBitmap(bitmap);
        int btwidth = options.outWidth;     // 获取图片的宽度
        int btheight = options.outHeight;   //获取图片的高度

        int dx = btwidth/100;    // 获取水平方向的缩放比例
        int dy = btheight/100;    // 获取垂直方向的缩放比例

        int sampleSize = 1; // 设置默认缩放比例

        // 如果是水平方向
        if (dx>dy&&dy>1) {
            sampleSize = dx;
        }

        //如果是垂直方向
        if (dy>dx&&dx>1) {
            sampleSize = dy;
        }
        options.inSampleSize = sampleSize;       // 设置图片缩放比例
        options.inJustDecodeBounds = false;     // 真正解析位图
        // 把图片的解析条件options在创建的时候带上
        bitmap = BitmapFactory.decodeFile(imagePath, options);

        ByteArrayOutputStream BAOStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, BAOStream);// (0-100)压缩文件
        byte[] image = BAOStream.toByteArray();
        databaseManage.updateCharacterCHCImageByte(image);
    }

    private Bitmap getBigHead(){
        //从数据库调取头像
        Character character = databaseManage.queryCharacter();
        // 把图片转为bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(character.CHCImagebyte, 0, character.CHCImagebyte.length);
        bigHead.setImageBitmap(bitmap);
        return bitmap;
    }

    @Override
    public void onBackPressed() {
        /*主界面背景音乐播放*/
        Intent intentMusic1 = new Intent(Map_Main.this, MyService1.class);
        startService(intentMusic1);

        /*课程背景音乐停止*/
        Intent intentMusic2 = new Intent(Map_Main.this, MyService2.class);
        stopService(intentMusic2);

        finish();
    }
}
