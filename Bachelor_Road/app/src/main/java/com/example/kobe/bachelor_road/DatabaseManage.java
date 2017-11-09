package com.example.kobe.bachelor_road;

/**
 *
 * 对数据库的操作
 *
 * Created by Administrator on 2017/11/7.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.*;

public class DatabaseManage {

    private Database database;

    public DatabaseManage(Context context) {
        database = new Database(context);//继承自带的SQLiteOpenHelper类，建数据库
    }

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
        values.put("CHIsFirstLogin", character.getCHIsFirstLogin());
        if( sQLiteDatabase.insert("Character", null, values) < 0)  // 插入第一条数据
            return "插入失败！";
        return "插入成功！";
    }

    //查询人物
    public Character queryCharacter(){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase(); //以读写方式打开数据库
        Cursor cursor = sQLiteDatabase.query("Character",null,null,null,null,null,null);//查询并获得游标
        Character character = new Character();
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
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
            float floatCHCredit = Float.parseFloat(CHCredit);
            String CHComprehensiveTest = cursor.getString(cursor.getColumnIndex("CHComprehensiveTest"));
            float floatCHComprehensiveTest = Float.parseFloat(CHComprehensiveTest);
            int CHCurrentTime = cursor.getInt(cursor.getColumnIndex("CHCurrentTime"));
            int CHCurrentWeek = cursor.getInt(cursor.getColumnIndex("CHCurrentWeek"));
            boolean CHIsFirstLogin = cursor.getInt(cursor.getColumnIndex("CHIsFirstLogin")) > 0;
            character.setCharacter( CHid , CHName, CHNo, CHClass,CHImage,intCHCurrentEnergy,intCHMaximumEnergy,
                    floatCHCredit,floatCHComprehensiveTest, CHCurrentTime,CHCurrentWeek,CHIsFirstLogin);
        }
        return character;
    }

    //查询姓名
    public String queryCHName(){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase(); //以读写方式打开数据库
        Cursor cursor = sQLiteDatabase.query("Character",null,null,null,null,null,null);//查询并获得游标
        String CHName = "";
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            CHName = cursor.getString(cursor.getColumnIndex("CHName"));
        }
        return CHName;
    }

    //查询活力
    public String queryCHCurrentEnergy(){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase(); //以读写方式打开数据库
        Cursor cursor = sQLiteDatabase.query("Character",null,null,null,null,null,null);//查询并获得游标
        String CHCurrentEnergy = "";
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            CHCurrentEnergy = cursor.getString(cursor.getColumnIndex("CHCurrentEnergy"));
        }
        return CHCurrentEnergy;
    }
    //查询是否为第一次登录
    public String queryCHIsFirstLogin() {
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase(); //以读写方式打开数据库
        Cursor cursor = sQLiteDatabase.query("Character",null,null,null,null,null,null);//查询并获得游标
        String CHIsFirstLogin = "";
        if(cursor.getCount() != 0){
            CHIsFirstLogin = "false";
        }
        else {
            CHIsFirstLogin = "true";
        }
        return CHIsFirstLogin;
    }







    //修改活力值
    public String updateCHCurrentEnergy(int CHCurrentEnergy){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase(); //以读写方式打开数据库
        ContentValues values = new ContentValues();
        values.clear();
        values.put("CHCurrentEnergy", String.valueOf(CHCurrentEnergy));
        if( sQLiteDatabase.update("Character", values, null, null) < 0)  //参数依次是表名，修改后的值，where条件，以及约束，如果不指定三四两个参数，会更改所有行
            return "更新失败！";
        return "更新成功！";
    }

    //

}
