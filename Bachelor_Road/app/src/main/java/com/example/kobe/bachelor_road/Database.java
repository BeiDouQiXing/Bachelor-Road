package com.example.kobe.bachelor_road;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 创建数据库，建表
 *
 * Created by Administrator on 2017/11/6.
 */

public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "XueShiZhiLu.db"; //数据库名称
    private static final int version =  1; //数据库版本
    public Database(Context context) {
        super( context, DB_NAME, null, version);
    }
    //数据库第一次创建时被调用
    @Override
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        //建游戏人物表
        String createCharacterSql = "create table Character (" +
                                        "CHid                 int not null," +
                                        "CHName               varchar(20)," +
                                        "CHNo                 varchar(20)," +
                                        "CHClass              int," +
                                        "CHImage              int," +
                                        "CHCurrentEnergy      varchar(20)," +
                                        "CHMaximumEnergy      varchar(20)," +
                                        "CHCredit             varchar(20)," +
                                        "CHComprehensiveTest  varchar(20)," +
                                        "CHCurrentTime        varchar(30)," +
                                        "CHCurrentWeek        int," +
                                        "CHIsFirstLogin       bool," +
                                        "primary key (CHid) );";
        sQLiteDatabase.execSQL(createCharacterSql);

        //建课程表
        String createCouseSql = "create table Course (" +
                                    "   Cid                  int not null," +
                                    "   CName                varchar(30)," +
                                    "   CCredit              varchar(20)," +
                                    "   CEachClassEnergy     varchar(20)," +
                                    "   CEachClassCredit     varchar(20)," +
                                    "   primary key (Cid) );";
        sQLiteDatabase.execSQL(createCouseSql);

        //建人物课程表
        String createCharacterCourseSql = "create table CharacterCourse" +
                                            "(" +
                                            "   CHCNum                int not null," +  //本节课该周内编号
                                            "   CHCWeek               int not null," +  //本节课第几周
                                            "   Cid                   int," +   //课程id
                                            "   CHid                  int," +   //人物id
                                            "   CHCSchooltimeWeek     int," +   //第几周
                                            "   CHCSchooltimeClass    int," +   //第几节
                                            "   CHCClassLocationBuild varchar(20)," +    //上课楼号
                                            "   CHCClassLocationClass varchar(20)," +    //上课教室
                                            "   CHCIsAttendClass      bool," +          //是否有参与本节课
                                            "   primary key (CHCNum, CHCWeek)" +
                                            ");";
        sQLiteDatabase.execSQL(createCharacterCourseSql);

        //建部门
        String createDepartmentSql = "create table Department" +
                                        "(" +
                                        "   Did                  int not null," +
                                        "   CHid                 int," +
                                        "   DName                varchar(30)," +
                                        "   DIsJoinDepartment    bool," +
                                        "   primary key (Did)" +
                                        ");";
        sQLiteDatabase.execSQL(createDepartmentSql);

        //建部门活动
        String createDepartmentActivitiesSql = "create table DepartmentActivities" +
                                                    "(" +
                                                    "   DAid                 int not null," +
                                                    "   CHid                 int," +
                                                    "   Did                  int," +
                                                    "   DAName               varchar(30)," +
                                                    "   DAActivityTime       varchar(30)," +    //活动时间
                                                    "   DAComprehensiveTest  varchar(20)," +        //部门活动综测
                                                    "   DAIsJoinActivity     bool," +
                                                    "   primary key (DAid)" +
                                                    ");";
        sQLiteDatabase.execSQL(createDepartmentActivitiesSql);
    }


    //软件版本号发生改变时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
