package com.xueshizhilu.xueshizhilusql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/11/6.
 */

public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "XueShiZhiLu.db"; //数据库名称
    private static final int version = 1; //数据库版本
    public Database(Context context) {
        super( context, DB_NAME, null, version);
    }
    //数据库第一次创建时被调用
    @Override
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        //建游戏人物表
        String createCharacterSql = "create table Character (" +
                                        "CHid                 int not null," +
                                        "CHName               varchar(50)," +
                                        "CHNo                 varchar(20)," +
                                        "CHClass              int," +
                                        "CHImage              int," +
                                        "CHCurrentEnergy      varchar(20)," +
                                        "CHMaximumEnergy      varchar(20)," +
                                        "CHCredit             varchar(20)," +
                                        "CHComprehensiveTest  varchar(20)," +
                                        "CHCurrentTime        int," +
                                        "CHCurrentWeek        int," +
                                        "primary key (CHid) );";
        sQLiteDatabase.execSQL(createCharacterSql);

        //建课程表
        String createCouseSql = "create table Course (" +
                                    "   Cid                  int not null," +
                                    "   CName                varchar(50)," +
                                    "   CEachClassEnergy     varchar(20)," +
                                    "   CCredit              varchar(20)," +
                                    "   CEachClassCredit     varchar(20)," +
                                    "   primary key (Cid) );";
        sQLiteDatabase.execSQL(createCouseSql);

        /*//插入课程
        Course[] course = new Course[8];
        course[0].setCourse(1, "高数", 8, 5, 0.09804);
        course[1].setCourse(2, "高级语言程序设计", 8, 4, 0.07843);
        course[2].setCourse(3, "计算机导论", 5, 1.5, 0.04412);
        course[3].setCourse(4, "线性代数", 6, 2, 0.05882);
        course[4].setCourse(5, "大学英语", 6, 2.5, 0.07353);
        course[5].setCourse(6, "思想道德修养与法律基础", 4, 2, 0.05882);
        course[6].setCourse(7, "体育", 5, 1, 0.05882);
        course[7].setCourse(8, "晚自习", 1, 1, 0.00952);
        String insertCouseSql = "insert into Course( Cid, CName, CEachClassEnergy, CCredit, CEachClassCredit) VALUES ( ?, ?, ?, ?, ?)";
        //sQLiteDatabase.beginTransaction();
        SQLiteStatement sQLiteStatementinsertCouse = sQLiteDatabase.compileStatement(insertCouseSql);
        for (int i = 0; i < course.length ; i++) {
            sQLiteStatementinsertCouse.bindLong( 1, course[i].getCid());
            sQLiteStatementinsertCouse.bindString( 2, course[i].getCName());
            sQLiteStatementinsertCouse.bindString( 3, String.valueOf(course[i].getCEachClassEnergy()));
            sQLiteStatementinsertCouse.bindString( 4, String.valueOf(course[i].getCCredit()));
            sQLiteStatementinsertCouse.bindString( 5, String.valueOf(course[i].getCEachClassCredit()));
            sQLiteStatementinsertCouse.executeInsert();
            sQLiteStatementinsertCouse.clearBindings();
        }
       //sQLiteDatabase.setTransactionSuccessful();
       //sQLiteDatabase.endTransaction();*/

        sQLiteDatabase.execSQL("insert into Course values(1, '高数', '8', '5', '0.09804')");
        sQLiteDatabase.execSQL("insert into Course values(2, '高级语言程序设计', '8', '4', '0.07843')");
        sQLiteDatabase.execSQL("insert into Course values(3, '计算机导论', '5', '1.5', '0.04412')");
        sQLiteDatabase.execSQL("insert into Course values(4, '线性代数', '6', '2', '0.05882')");
        sQLiteDatabase.execSQL("insert into Course values(5, '大学英语', '6', '2.5', '0.07353')");
        sQLiteDatabase.execSQL("insert into Course values(6, '思想道德修养与法律基础', '4', '2', '0.05882')");
        sQLiteDatabase.execSQL("insert into Course values(7, '体育', '5', '1', '0.05882')");
        sQLiteDatabase.execSQL("insert into Course values(8, '晚自习', '1', '1', '0.00952')");

        //建人物课程表
        String createCharacterCourseSql = "create table CharacterCourse" +
                                            "(" +
                                            "   CHCid                 int not null," +  //本节课该周内编号
                                            "   CHCWeek               int," +  //本节课第几周
                                            "   Cid                   int," +   //课程id
                                            "   CHCSchooltimeWeek     int," +   //第几周
                                            "   CHCSchooltimeClass    int," +   //第几节
                                            "   CHCClassLocationBuild varchar(20)," +    //上课楼号
                                            "   CHCClassLocationClass varchar(20)," +    //上课教室
                                            "   CHCIsAttendClass      bool," +          //是否有参与本节课
                                            "   primary key (CHCid)" +
                                            ");";
        sQLiteDatabase.execSQL(createCharacterCourseSql);

        //插入人物课程表
        sQLiteDatabase.execSQL("insert into CharacterCourse values(1, 2, 1, 1, 1, '西2', '301', false)");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(2, 2, 1, 3, 2, '西2', '301', false)");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(3, 2, 1, 5, 1, '西2', '301', false)");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(4, 2, 2, 2, 2, '西2', '301', false)");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(5, 2, 2, 3, 3, '西2', '301', false)");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(6, 2, 2, 4, 1, '西2', '301', false)");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(7, 2, 3, 1, 1, '西2', '301', false)");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(8, 2, 3, 5, 2, '西2', '301', false)");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(9, 2, 4, 1, 2, '西2', '301', false)");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(10, 2, 4, 3, 1, '西2', '301', false)");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(11, 2, 5, 5, 4, '西2', '301', false)");

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
