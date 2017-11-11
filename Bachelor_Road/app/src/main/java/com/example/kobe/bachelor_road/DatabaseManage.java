package com.xueshizhilu.xueshizhilusql;

/**
 * Created by Administrator on 2017/11/7.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.*;
import android.util.Log;
import java.lang.*;

public class DatabaseManage {

    private Database database;

    public DatabaseManage(Context context) {
        database = new Database(context);//继承自带的SQLiteOpenHelper类，建数据库
    }

    /*********************************************
     对 Character 表的操作
     ********************************************/
    //插入一条人物记录
    public  String addCharacter(Character character){
        SQLiteDatabase sQLiteDatabase = database.getReadableDatabase(); //以读写方式打开数据库
        Cursor cursor = sQLiteDatabase.query("Character",null,null,null,null,null,null);//查询并获得游标
        if(cursor.getCount() != 0)
            return "已经注册";
        ContentValues values = new ContentValues();// 开始组装数据
        values.clear();
        values.put("CHid", character.getCHid());
        values.put("CHName", character.getCHName());
        values.put("CHNo", character.getCHNo());
        values.put("CHClass", character.getCHClass());
        values.put("CHImage", character.getCHImage());
        values.put("CHCurrentEnergy", String.valueOf(character.getCHCurrentEnergy()) );
        values.put("CHMaximumEnergy", String.valueOf(character.getCHMaximumEnergy()) );
        values.put("CHCredit", String.valueOf(character.getCHCredit()) );
        values.put("CHComprehensiveTest", String.valueOf(character.getCHComprehensiveTest()) );
        values.put("CHCurrentTime", String.valueOf(character.getCHCurrentTime()));
        values.put("CHCurrentWeek", character.getCHCurrentWeek());
        if( sQLiteDatabase.insert("Character", null, values) < 0)  // 插入第一条数据
            return "插入失败！";
        return "插入成功！";
    }

    //查询人物
    public Character queryCharacter(){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase(); //以读写方式打开数据库
        Cursor cursor = sQLiteDatabase.query("Character",null,null,null,null,null,null);//查询并获得游标
        Character character = new Character();
        if(cursor.moveToNext()) {
            int CHid = cursor.getInt(cursor.getColumnIndex("CHid"));
            String CHName = cursor.getString(cursor.getColumnIndex("CHName"));
            String CHNo = cursor.getString(cursor.getColumnIndex("CHNo"));
            int CHClass = cursor.getInt(cursor.getColumnIndex("CHClass"));
            int CHImage = cursor.getInt(cursor.getColumnIndex("CHImage"));
            String CHCurrentEnergy = cursor.getString(cursor.getColumnIndex("CHCurrentEnergy"));
            int intCHCurrentEnergy = Integer.valueOf(CHCurrentEnergy);
            String CHMaximumEnergy = cursor.getString(cursor.getColumnIndex("CHMaximumEnergy"));
            int intCHMaximumEnergy = Integer.valueOf(CHMaximumEnergy);
            String CHCredit = cursor.getString(cursor.getColumnIndex("CHCredit"));
            double floatCHCredit = Double.parseDouble(CHCredit);
            String CHComprehensiveTest = cursor.getString(cursor.getColumnIndex("CHComprehensiveTest"));
            double floatCHComprehensiveTest = Double.parseDouble(CHComprehensiveTest);
            int CHCurrentTime = cursor.getInt(cursor.getColumnIndex("CHCurrentTime"));
            int CHCurrentWeek = cursor.getInt(cursor.getColumnIndex("CHCurrentWeek"));
            character.setCharacter( CHid , CHName, CHNo, CHClass,CHImage,intCHCurrentEnergy,intCHMaximumEnergy,
                    floatCHCredit,floatCHComprehensiveTest, CHCurrentTime,CHCurrentWeek);
        }
        sQLiteDatabase.close();
        return character;
    }

    //查询姓名
    public String queryCHName(){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase(); //以读写方式打开数据库
        Cursor cursor = sQLiteDatabase.query("Character",null,null,null,null,null,null);//查询并获得游标
        String CHName = "";
        if(cursor.moveToNext()) {
            CHName = cursor.getString(cursor.getColumnIndex("CHName"));
        }
        sQLiteDatabase.close();
        return CHName;
    }

    //查询活力
    public String queryCHCurrentEnergy(){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase(); //以读写方式打开数据库
        Cursor cursor = sQLiteDatabase.query("Character",null,null,null,null,null,null);//查询并获得游标
        String CHCurrentEnergy = "";
        if(cursor.moveToNext()) {
            CHCurrentEnergy = cursor.getString(cursor.getColumnIndex("CHCurrentEnergy"));
        }
        sQLiteDatabase.close();
        return CHCurrentEnergy;
    }

    //查询当前时间
    public int queryCHCurrentTime(){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase(); //以读写方式打开数据库
        Cursor cursor = sQLiteDatabase.query("Character",null,null,null,null,null,null);//查询并获得游标
        int CHCurrentTime = -1;
        if(cursor.moveToNext()) {
            CHCurrentTime = cursor.getInt(cursor.getColumnIndex("CHCurrentTime"));
        }
        sQLiteDatabase.close();
        return CHCurrentTime;
    }

    //查询当前第几周
    public int queryCHCurrentWeek(){
        SQLiteDatabase sQLiteDatabase = database.getReadableDatabase(); //以读写方式打开数据库
        Cursor cursor = sQLiteDatabase.query("Character",null,null,null,null,null,null);//查询并获得游标
        int CHCurrentWeek = -1;
        if(cursor.moveToNext()) {
            CHCurrentWeek = cursor.getInt(cursor.getColumnIndex("CHCurrentWeek"));
        }
        sQLiteDatabase.close();
        return CHCurrentWeek;
    }

    //查询是否为第一次登录
    public String queryCHIsFirstLogin(){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase(); //以读写方式打开数据库
        Cursor cursor = sQLiteDatabase.query("Character",null,null,null,null,null,null);//查询并获得游标
        String CHIsFirstLogin = "";
        if(cursor.getCount() != 0)
            CHIsFirstLogin = "false";
        else
            CHIsFirstLogin = "true";
        sQLiteDatabase.close();
        return CHIsFirstLogin;
    }



    //修改活力值
    public String updateCHCurrentEnergy(int CHCurrentEnergy){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase(); //以读写方式打开数据库
        ContentValues values = new ContentValues();
        values.clear();
        values.put("CHCurrentEnergy", String.valueOf(CHCurrentEnergy));
        String results = "更新成功！";
        if( sQLiteDatabase.update("Character", values, null, null) < 0)
            results = "更新失败！";
        sQLiteDatabase.close();
        return results;
    }

    //修改活力值上限


    //修改当前时间
    public String updateCHCurrentTime(int CHCurrentTime){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.clear();
        values.put("CHCurrentTime", CHCurrentTime);
        String results = "更新成功！";
        if( sQLiteDatabase.update("Character", values, null, null) < 0)
            results = "更新失败！";
        sQLiteDatabase.close();
        return results;
    }

    //修改当前周数
    public String updateCHCurrentWeek(int CHCurrentWeek){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.clear();
        values.put("CHCurrentWeek", CHCurrentWeek);
        String results = "更新成功！";
        if( sQLiteDatabase.update("Character", values, null, null) < 0 )
            results = "更新失败！";
        sQLiteDatabase.close();
        return results;
    }


    /*********************************************
     对 Course 表的操作
     ********************************************/
    //查询 课表
    public Course[] queryCourse(){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        Cursor cursor = sQLiteDatabase.query("Course",null,null,null,null,null,null);//查询并获得游标
        Course[] course = new Course[cursor.getCount()];
        for (int i=0; cursor.moveToNext() == true; i++ ){
            int Cid = cursor.getInt(cursor.getColumnIndex("Cid"));
            String CName = cursor.getString(cursor.getColumnIndex("CName"));
            int CEachClassEnergy = Integer.valueOf(cursor.getString(cursor.getColumnIndex("CEachClassEnergy")));
            double CCredit = Double.valueOf(cursor.getString(cursor.getColumnIndex("CCredit")));
            double CEachClassCredit = Double.valueOf(cursor.getString(cursor.getColumnIndex("CEachClassCredit")));
            course[i] = new Course(Cid,CName,CEachClassEnergy,CCredit,CEachClassCredit);
        }
        sQLiteDatabase.close();
        return course;
    }

}
