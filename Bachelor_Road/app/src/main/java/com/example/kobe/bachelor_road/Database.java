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

        /**********建人物表**********/
        String createCharacterSql = "create table Character (" +
                                        "CHid                 int not null," +   //人物id
                                        "CHName               varchar(50)," +    //人物名
                                        "CHNo                 varchar(20)," +
                                        "CHClass              int," +
                                        "CHImage              int," +
                                        "CHCurrentEnergy      varchar(20)," +   //人物活力值
                                        "CHMaximumEnergy      varchar(20)," +   //人物活力值上限
                                        "CHCredit             varchar(20)," +   //学分
                                        "CHComprehensiveTest  varchar(20)," +   //综测
                                        "CHCurrentTime        int," +           //当前时间
                                        "CHCurrentWeek        int," +           //当前周
                                        "primary key (CHid) );";
        sQLiteDatabase.execSQL(createCharacterSql);

        /**************建课程表*********/
        String createCouseSql = "create table Course (" +
                                    "   Cid                  int not null," +  //课程号
                                    "   CName                varchar(50)," +    //课程名
                                    "   CEachClassEnergy     varchar(20)," +   //每节课消耗活力值
                                    "   CCredit              varchar(20)," +   //课程学分
                                    "   CEachClassCredit     varchar(20)," +   //每节课学分
                                    "   primary key (Cid) );";
        sQLiteDatabase.execSQL(createCouseSql);

        /*************插入课程************/
        /*Course[] course = new Course[8];
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

        /*************插入课程************/
        sQLiteDatabase.execSQL("insert into Course values(	1, '高数',             '8', '5', '0.09804')");
        sQLiteDatabase.execSQL("insert into Course values(	2, '高级语言程序设计', '8', '4', '0.07843')");
        sQLiteDatabase.execSQL("insert into Course values(	3, '计算机导论',       '5', '1.5', '0.04412')");
        sQLiteDatabase.execSQL("insert into Course values(	4, '线性代数',         '6', '2', '0.05882')");
        sQLiteDatabase.execSQL("insert into Course values(	5, '大学英语',         '6', '2.5', '0.07353')");
        sQLiteDatabase.execSQL("insert into Course values(	6, '思想道德修养与法律基础', '4', '2', '0.05882')");
        sQLiteDatabase.execSQL("insert into Course values(	7, '体育',             '5', '1', '0.05882')");
        sQLiteDatabase.execSQL("insert into Course values(	8, '晚自习',           '1', '1', '0.00952')");

        /****************建人物课程关系表*****************/
        String createCharacterCourseSql = "create table CharacterCourse" +
                                            "(" +
                                            "   CHCid                 int not null," +  //本节课该周内编号
                                            "   CHCWeek               int," +           //本节课第几周
                                            "   Cid                   int," +           //课程id
                                            "   CHCSchooltimeWeek     int," +           //周几
                                            "   CHCSchooltimeClass    int," +           //第几节
                                            "   CHCClassLocation      varchar(30)," +    //上课地点
                                            "   CHCIsAttendClass      varchar(10)," +   //是否有参与本节课
                                            "   primary key (CHCid)" +
                                            ");";
        sQLiteDatabase.execSQL(createCharacterCourseSql);

        /******************插入人物课程表****************/
        sQLiteDatabase.execSQL("insert into CharacterCourse values(  1, 2, 1, 1, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(  2, 2, 4, 1, 2, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(  3, 2, 6, 1, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(  4, 2, 8, 1, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(  5, 2, 5, 2, 1, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(  6, 2, 2, 2, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(  7, 2, 7, 2, 4, '风雨操场', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(  8, 2, 8, 2, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(  9, 2, 4, 3, 1, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 10, 2, 1, 3, 2, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 11, 2, 2, 3, 3, '东3-303', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 12, 2, 3, 3, 4, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 13, 2, 8, 3, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 14, 2, 2, 4, 1, '数计中-304','false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 15, 2, 8, 4, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 16, 2, 1, 5, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 17, 2, 3, 5, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 18, 2, 6, 5, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 19, 2, 5, 5, 4, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 20, 2, 8, 7, 5, '东3-305', 'false')");
        //第三周
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 21, 3, 1, 1, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 22, 3, 4, 1, 2, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 23, 3, 6, 1, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 24, 3, 8, 1, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 25, 3, 5, 2, 1, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 26, 3, 2, 2, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 27, 3, 7, 2, 4, '风雨操场', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 28, 3, 8, 2, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 29, 3, 4, 3, 1, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 30, 3, 1, 3, 2, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 31, 3, 2, 3, 3, '东3-303', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 32, 3, 3, 3, 4, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 33, 3, 8, 3, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 34, 3, 2, 4, 1, '数计中-304', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 35, 3, 8, 4, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 36, 3, 1, 5, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 37, 3, 3, 5, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 38, 3, 6, 5, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 39, 3, 5, 5, 4, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 40, 3, 8, 7, 5, '东3-305', 'false')");
        //第四周
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 41, 4, 1, 1, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 42, 4, 4, 1, 2, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 43, 4, 6, 1, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 44, 4, 8, 1, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 45, 4, 5, 2, 1, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 46, 4, 2, 2, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 47, 4, 7, 2, 4, '风雨操场', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 48, 4, 8, 2, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 49, 4, 4, 3, 1, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 50, 4, 1, 3, 2, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 51, 4, 2, 3, 3, '东3-303', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 52, 4, 3, 3, 4, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 53, 4, 8, 3, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 54, 4, 2, 4, 1, '数计中-304', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 55, 4, 8, 4, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 56, 4, 1, 5, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 57, 4, 3, 5, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 58, 4, 6, 5, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 59, 4, 5, 5, 4, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 60, 4, 8, 7, 5, '东3-305', 'false')");
        //第五周
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 61, 5, 1, 1, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 62, 5, 4, 1, 2, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 63, 5, 6, 1, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 64, 5, 8, 1, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 65, 5, 5, 2, 1, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 66, 5, 2, 2, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 67, 5, 7, 2, 4, '风雨操场', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 68, 5, 8, 2, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 69, 5, 4, 3, 1, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 70, 5, 1, 3, 2, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 71, 5, 2, 3, 3, '东3-303', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 72, 5, 3, 3, 4, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 73, 5, 8, 3, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 74, 5, 2, 4, 1, '数计中-304', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 75, 5, 8, 4, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 76, 5, 1, 5, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 77, 5, 3, 5, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 78, 5, 6, 5, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 79, 5, 5, 5, 4, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 80, 5, 8, 7, 5, '东3-305', 'false')");
        //第六周
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 81, 6, 1, 1, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 82, 6, 4, 1, 2, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 83, 6, 6, 1, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 84, 6, 8, 1, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 85, 6, 5, 2, 1, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 86, 6, 2, 2, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 87, 6, 7, 2, 4, '风雨操场', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 88, 6, 8, 2, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 89, 6, 4, 3, 1, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 90, 6, 1, 3, 2, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 91, 6, 2, 3, 3, '东3-303', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 92, 6, 3, 3, 4, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 93, 6, 8, 3, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 94, 6, 2, 4, 1, '数计中-304', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 95, 6, 8, 4, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 96, 6, 1, 5, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 97, 6, 3, 5, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 98, 6, 6, 5, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values( 99, 6, 5, 5, 4, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(100, 6, 8, 7, 5, '东3-305', 'false')");
        //第七周
        sQLiteDatabase.execSQL("insert into CharacterCourse values(101, 7, 1, 1, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(102, 7, 4, 1, 2, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(103, 7, 6, 1, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(104, 7, 8, 1, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(105, 7, 5, 2, 1, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(106, 7, 2, 2, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(107, 7, 7, 2, 4, '风雨操场', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(108, 7, 8, 2, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(109, 7, 4, 3, 1, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(110, 7, 1, 3, 2, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(111, 7, 2, 3, 3, '东3-303', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(112, 7, 3, 3, 4, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(113, 7, 8, 3, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(114, 7, 2, 4, 1, '数计中-304', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(115, 7, 8, 4, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(116, 7, 1, 5, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(117, 7, 3, 5, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(118, 7, 6, 5, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(119, 7, 5, 5, 4, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(120, 7, 8, 7, 5, '东3-305', 'false')");
        //第八周
        sQLiteDatabase.execSQL("insert into CharacterCourse values(121, 8, 1, 1, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(122, 8, 4, 1, 2, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(123, 8, 6, 1, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(124, 8, 8, 1, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(125, 8, 5, 2, 1, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(126, 8, 2, 2, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(127, 8, 7, 2, 4, '风雨操场', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(128, 8, 8, 2, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(129, 8, 4, 3, 1, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(130, 8, 1, 3, 2, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(131, 8, 2, 3, 3, '东3-303', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(132, 8, 3, 3, 4, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(133, 8, 8, 3, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(134, 8, 2, 4, 1, '数计中-304', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(135, 8, 8, 4, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(136, 8, 1, 5, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(137, 8, 3, 5, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(138, 8, 6, 5, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(139, 8, 5, 5, 4, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(140, 8, 8, 7, 5, '东3-305', 'false')");
        //第九周
        sQLiteDatabase.execSQL("insert into CharacterCourse values(141, 9, 1, 1, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(142, 9, 4, 1, 2, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(143, 9, 6, 1, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(144, 9, 8, 1, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(145, 9, 5, 2, 1, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(146, 9, 2, 2, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(147, 9, 7, 2, 4, '风雨操场', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(148, 9, 8, 2, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(149, 9, 4, 3, 1, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(150, 9, 1, 3, 2, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(151, 9, 2, 3, 3, '东3-303', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(152, 9, 3, 3, 4, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(153, 9, 8, 3, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(154, 9, 2, 4, 1, '数计中-304', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(155, 9, 8, 4, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(156, 9, 1, 5, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(157, 9, 3, 5, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(158, 9, 6, 5, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(159, 9, 5, 5, 4, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(160, 9, 8, 7, 5, '东3-305', 'false')");
        //第十周
        sQLiteDatabase.execSQL("insert into CharacterCourse values(161, 10, 1, 1, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(162, 10, 4, 1, 2, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(163, 10, 6, 1, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(164, 10, 8, 1, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(165, 10, 5, 2, 1, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(166, 10, 2, 2, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(167, 10, 7, 2, 4, '风雨操场', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(168, 10, 8, 2, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(169, 10, 4, 3, 1, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(170, 10, 1, 3, 2, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(171, 10, 2, 3, 3, '东3-303', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(172, 10, 3, 3, 4, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(173, 10, 8, 3, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(174, 10, 2, 4, 1, '数计中-304', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(175, 10, 8, 4, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(176, 10, 1, 5, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(177, 10, 3, 5, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(178, 10, 6, 5, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(179, 10, 5, 5, 4, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(180, 10, 8, 7, 5, '东3-305', 'false')");
        //第十一周
        sQLiteDatabase.execSQL("insert into CharacterCourse values(181, 11, 1, 1, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(182, 11, 4, 1, 2, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(183, 11, 6, 1, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(184, 11, 8, 1, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(185, 11, 5, 2, 1, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(186, 11, 2, 2, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(187, 11, 7, 2, 4, '风雨操场', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(188, 11, 8, 2, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(189, 11, 4, 3, 1, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(190, 11, 1, 3, 2, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(191, 11, 2, 3, 3, '东3-303', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(192, 11, 3, 3, 4, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(193, 11, 8, 3, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(194, 11, 2, 4, 1, '数计中-304', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(195, 11, 8, 4, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(196, 11, 1, 5, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(197, 11, 3, 5, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(198, 11, 6, 5, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(199, 11, 5, 5, 4, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(200, 11, 8, 7, 5, '东3-305', 'false')");
        //第十二周
        sQLiteDatabase.execSQL("insert into CharacterCourse values(201, 12, 1, 1, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(202, 12, 4, 1, 2, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(203, 12, 6, 1, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(204, 12, 8, 1, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(205, 12, 5, 2, 1, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(206, 12, 2, 2, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(207, 12, 7, 2, 4, '风雨操场', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(208, 12, 8, 2, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(209, 12, 4, 3, 1, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(210, 12, 1, 3, 2, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(211, 12, 2, 3, 3, '东3-303', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(212, 12, 3, 3, 4, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(213, 12, 8, 3, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(214, 12, 2, 4, 1, '数计中-304', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(215, 12, 8, 4, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(216, 12, 1, 5, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(217, 12, 3, 5, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(218, 12, 6, 5, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(219, 12, 5, 5, 4, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(220, 12, 8, 7, 5, '东3-305', 'false')");
        //第十三周
        sQLiteDatabase.execSQL("insert into CharacterCourse values(221, 13, 1, 1, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(222, 13, 4, 1, 2, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(223, 13, 6, 1, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(224, 13, 8, 1, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(225, 13, 5, 2, 1, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(226, 13, 2, 2, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(227, 13, 7, 2, 4, '风雨操场', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(228, 13, 8, 2, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(229, 13, 4, 3, 1, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(230, 13, 1, 3, 2, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(231, 13, 2, 3, 3, '东3-303', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(232, 13, 3, 3, 4, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(233, 13, 8, 3, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(234, 13, 2, 4, 1, '数计中-304', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(235, 13, 8, 4, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(236, 13, 1, 5, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(237, 13, 3, 5, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(238, 13, 6, 5, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(239, 13, 5, 5, 4, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(240, 13, 8, 7, 5, '东3-305', 'false')");
        //第十四周
        sQLiteDatabase.execSQL("insert into CharacterCourse values(241, 14, 1, 1, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(242, 14, 4, 1, 2, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(243, 14, 6, 1, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(244, 14, 8, 1, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(245, 14, 5, 2, 1, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(246, 14, 2, 2, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(247, 14, 7, 2, 4, '风雨操场', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(248, 14, 8, 2, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(249, 14, 4, 3, 1, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(250, 14, 1, 3, 2, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(251, 14, 2, 3, 3, '东3-303', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(252, 14, 3, 3, 4, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(253, 14, 8, 3, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(254, 14, 2, 4, 1, '数计中-304', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(255, 14, 8, 4, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(256, 14, 1, 5, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(257, 14, 3, 5, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(258, 14, 6, 5, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(259, 14, 5, 5, 4, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(260, 14, 8, 7, 5, '东3-305', 'false')");
        //第十五周
        sQLiteDatabase.execSQL("insert into CharacterCourse values(261, 15, 1, 1, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(262, 15, 4, 1, 2, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(263, 15, 6, 1, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(264, 15, 8, 1, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(265, 15, 5, 2, 1, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(266, 15, 2, 2, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(267, 15, 7, 2, 4, '风雨操场', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(268, 15, 8, 2, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(269, 15, 4, 3, 1, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(270, 15, 1, 3, 2, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(271, 15, 2, 3, 3, '东3-303', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(272, 15, 3, 3, 4, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(273, 15, 8, 3, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(274, 15, 2, 4, 1, '数计中-304', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(275, 15, 8, 4, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(276, 15, 1, 5, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(277, 15, 3, 5, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(278, 15, 6, 5, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(279, 15, 5, 5, 4, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(280, 15, 8, 7, 5, '东3-305', 'false')");
        //第十六周
        sQLiteDatabase.execSQL("insert into CharacterCourse values(281, 16, 1, 1, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(282, 16, 4, 1, 2, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(283, 16, 6, 1, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(284, 16, 8, 1, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(285, 16, 5, 2, 1, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(286, 16, 2, 2, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(287, 16, 7, 2, 4, '风雨操场', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(288, 16, 8, 2, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(289, 16, 4, 3, 1, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(290, 16, 1, 3, 2, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(291, 16, 2, 3, 3, '东3-303', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(292, 16, 3, 3, 4, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(293, 16, 8, 3, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(294, 16, 2, 4, 1, '数计中-304', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(295, 16, 8, 4, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(296, 16, 1, 5, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(297, 16, 3, 5, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(298, 16, 6, 5, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(299, 16, 5, 5, 4, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(300, 16, 8, 7, 5, '东3-305', 'false')");
        //第十七周
        sQLiteDatabase.execSQL("insert into CharacterCourse values(301, 17, 1, 1, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(302, 17, 4, 1, 2, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(303, 17, 6, 1, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(304, 17, 8, 1, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(305, 17, 5, 2, 1, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(306, 17, 2, 2, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(307, 17, 7, 2, 4, '风雨操场', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(308, 17, 8, 2, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(309, 17, 4, 3, 1, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(310, 17, 1, 3, 2, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(311, 17, 2, 3, 3, '东3-303', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(312, 17, 3, 3, 4, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(313, 17, 8, 3, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(314, 17, 2, 4, 1, '数计中-304', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(315, 17, 8, 4, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(316, 17, 1, 5, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(317, 17, 3, 5, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(318, 17, 6, 5, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(319, 17, 5, 5, 4, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(320, 17, 8, 7, 5, '东3-305', 'false')");
        //第十八周
        sQLiteDatabase.execSQL("insert into CharacterCourse values(321, 18, 1, 1, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(322, 18, 4, 1, 2, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(323, 18, 6, 1, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(324, 18, 8, 1, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(325, 18, 5, 2, 1, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(326, 18, 2, 2, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(327, 18, 7, 2, 4, '风雨操场', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(328, 18, 8, 2, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(329, 18, 4, 3, 1, '西3-103', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(330, 18, 1, 3, 2, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(331, 18, 2, 3, 3, '东3-303', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(332, 18, 3, 3, 4, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(333, 18, 8, 3, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(334, 18, 2, 4, 1, '数计中-304', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(335, 18, 8, 4, 5, '东3-305', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(336, 18, 1, 5, 1, '西3-201', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(337, 18, 3, 5, 2, '东3-506', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(338, 18, 6, 5, 3, '东2-505', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(339, 18, 5, 5, 4, '西1-405', 'false')");
        sQLiteDatabase.execSQL("insert into CharacterCourse values(340, 18, 8, 7, 5, '东3-305', 'false')");

        /*********************建部门表*****************/
        String createDepartmentSql = "create table Department" +
                                        "(" +
                                        "   Did                  int not null," +   //部门id
                                        "   DName                varchar(30)," +    //部门名称
                                        "   DFunctions           text," +           //部门职能
                                        "   DIsJoinDepartment    varchar(10)," +    //是否加入部门
                                        "   primary key (Did)" +
                                        ");";
        sQLiteDatabase.execSQL(createDepartmentSql);

        /********************插入部门表***************/
        sQLiteDatabase.execSQL("insert into Department values( 1, '文娱部', '1.负责协助学校有关部门开展促进大学生身心健康的文化活动；2.负责学校文艺演出、晚会策划、编排组织工作；3.负责选取和组织优秀文艺节目参加省市比赛和社会公益演出；4.负责有文艺特长和文艺爱好者定期进行交流；5.配合各部门完成其他工作。',  'false')");
        sQLiteDatabase.execSQL("insert into Department values( 2, '办公室', '1.负责协调、管理学生会各部门日常工作和事务的部门，2.对学院（或大学）和团委的工作进行上传下达，3.对大型活动进行谋划策划，4.或者协助组织学生会其他部门进行活动，5.提供场地、设备、道具的租赁、配发和购买！处理学生会日常文职工作。',  'false')");
        sQLiteDatabase.execSQL("insert into Department values( 3, '外联部', '1.促进学生会与外界进行学术、文化、艺术、文娱等方面交流；2.把兄弟院校成功的管理经验和精品活动引进我校，丰富各职能部门工作内容；3.与社会各界建立长期联系，为学生会的活动需求支持；4.配合各部门完成其他工作。',  'false')");
        sQLiteDatabase.execSQL("insert into Department values( 4, '体育部', '1.负责组织开展有利于大学生身心健康的体育活动，提高大学生身体素质；2.协助学校体育教研部开展全校性体育活动；3.负责学生跑操、划操、会操检查工作；4.负责组织与其他离校进行体育交流；5.配合各部门完成其他工作。',  'false')");
        sQLiteDatabase.execSQL("insert into Department values( 5, '宣传部', '1.负责围绕党的基本方针政策和学校党委的中心工作，对学生进行思想教育；2.负责向全校学生传递工作信息；3.负责校团委、学生会开展各列大型活动的宣传、策划工作；4.负责学生会工作动态的宣传报导；5.负责校学生会墙报、宣传栏的出版，促进校学生会与院学生会以及广大同学的联系与交流；',  'false')");

        /*******************建部门活动表************/
        String createDepartmentActivitiesSql = "create table DepartmentActivities" +
                                                    "(" +
                                                    "   DAid                 int not null," +       //部门活动id
                                                    "   DAWeek               int," +                //该活动第几周
                                                    "   Did                  int," +                //部门活动发起部门
                                                    "   DAName               varchar(30)," +        //活动名称
                                                    "   DABeginTime          int," +                //活动开始时间
                                                    "   DAEndTime            int," +                //活动结束时间
                                                    "   DALocation           varchar(20)," +        //部门活动地点
                                                    "   DAComprehensiveTest  varchar(20)," +        //部门活动综测
                                                    "   DAEnergy             varchar(20)," +        //参加活动消耗的活力
                                                    "   DAIsJoinActivity     varchar(20)," +        //是否参加
                                                    "   primary key (DAid)" +
                                                    ");";
        sQLiteDatabase.execSQL(createDepartmentActivitiesSql);


        /******************插入部门活动表*************/
        //第2周
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(  1, 2, 1, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(  2, 2, 1, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(  3, 2, 2, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(  4, 2, 2, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(  5, 2, 3, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(  6, 2, 3, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(  7, 2, 4, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(  8, 2, 4, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(  9, 2, 5, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 10, 2, 5, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 11, 2, 1, '打扫活动室', 2280, 2460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 12, 2, 1, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 13, 2, 1, '组织校十佳歌手比赛', 9780, 9900, '青春广场', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 14, 2, 2, '打扫活动室', 2280, 2460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 15, 2, 2, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 16, 2, 2, '活动室物品清点',9780, 9900, '活动室',     '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 17, 2, 3, '打扫活动室', 2280, 2460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 18, 2, 3, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 19, 2, 3, '前往新东方某某公司拉取赞助', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 20, 2, 4, '打扫活动室', 2280, 2460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 21, 2, 4, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 22, 2, 4, '举办3v3趣味篮球赛', 9780, 9900, '篮球馆', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 23, 2, 5, '打扫活动室', 2280, 2460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 24, 2, 5, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 25, 2, 5, '学校某某讲座采访记录', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        //第3周
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 26, 3, 1, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 27, 3, 1, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 28, 3, 2, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 29, 3, 2, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 30, 3, 3, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 31, 3, 3, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 32, 3, 4, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 33, 3, 4, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 34, 3, 5, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 35, 3, 5, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 36, 3, 1, '组织校迎新晚会', 8340, 8460, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 37, 3, 1, '组织某某电影的见面会', 6600, 6840, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 38, 3, 1, '百团纳新服务人员', 9240, 9360, '青春广场', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 39, 3, 2, '活动室借用管理', 8340, 8460, '活动室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 40, 3, 2, '团委消息传达', 6600, 6840, '活动室',     '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 41, 3, 2, '日常部门文案整理', 9240, 9360, '活动室',     '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 42, 3, 3, '前往星网锐捷某某公司拉取赞助', 8340, 8460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 43, 3, 3, '赞助要求条件完成（宣传展板、拱门等）', 9240, 9360, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 44, 3, 3, '赞助要求条件完成（举办宣讲会等）', 6600, 6840, '素拓',     '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 45, 3, 4, '篮球校联赛经管vs土木比赛记录', 8340, 8460, '篮球馆', '0.1', '10','false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 46, 3, 4, '篮球校联赛数计vs电气比赛记录', 6600, 6840, '篮球馆', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 47, 3, 4, '举办校友会友谊赛', 9240, 9360, '篮球馆', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 48, 3, 5, '学生会宣传栏信息更新', 8340, 8460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 49, 3, 5, '学校某某会议录影录像', 6600, 6840, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 50, 3, 5, '学校公众号信息更新', 9240, 9360, '素拓', '0.1', '10', 'false')");
        //第4周
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 51, 4, 1, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 52, 4, 1, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 53, 4, 2, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 54, 4, 2, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 55, 4, 3, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 56, 4, 3, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 57, 4, 4, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 58, 4, 4, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 59, 4, 5, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 60, 4, 5, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 61, 4, 1, '组织晚会', 4020, 4140, '青春广场', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 62, 4, 1, '组织某某书籍签售会', 5160, 5400, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 63, 4, 1, '百团纳新服务人员', 9240, 9360, '青春广场', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 64, 4, 2, '道具购买报销', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 65, 4, 2, '道具（展板，红旗等）管理配发', 5160, 5400, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 66, 4, 2, '日常部门文案整理', 9240, 9360, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 67, 4, 3, '前往学校领导汇报赞助情况', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 68, 4, 3, '前往某某电影院拉取赞助', 5160, 5400, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 69, 4, 3, '赞助要求条件完成（举办宣讲会等）', 6600, 6840, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 70, 4, 4, '前往学校领导汇报比赛情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10','false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 71, 4, 4, '篮球校联赛数计vs电气比赛记录', 6600, 6840, '篮球馆', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 72, 4, 4, '篮球校联赛法学vs化学比赛记录', 5160, 5400, '篮球馆', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 73, 4, 5, '前往学校领导汇报情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 74, 4, 5, '学校微博信息更新', 5160, 5400, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 75, 4, 5, '学校公众号信息更新', 9240, 9360, '素拓', '0.1',  '10', 'false')");
        //第5周
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 76, 5, 1, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 77, 5, 1, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 78, 5, 2, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 79, 5, 2, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 80, 5, 3, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 81, 5, 3, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 82, 5, 4, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 83, 5, 4, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 84, 5, 5, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 85, 5, 5, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 86, 5, 1, '组织某某电影的见面会', 6600, 6840, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 87, 5, 1, '组织某某书籍签售会', 5160, 5400, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 88, 5, 1, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 89, 5, 2, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 90, 5, 2, '活动室物品清点', 9780, 9900, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 91, 5, 2, '日常部门文案整理', 9240, 9360, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 92, 5, 3, '前往学校领导汇报赞助情况', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 93, 5, 3, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 94, 5, 3, '前往新东方某某公司拉取赞助', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 95, 5, 4, '前往学校领导汇报比赛情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10','false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 96, 5, 4, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 97, 5, 4, '举办3v3趣味篮球赛', 9780, 9900, '篮球馆', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 98, 5, 5, '前往学校领导汇报情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values( 99, 5, 5, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(100, 5, 5, '学校某某讲座采访记录', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        //第6周
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(101, 6, 1, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(102, 6, 1, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(103, 6, 2, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(104, 6, 2, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(105, 6, 3, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(106, 6, 3, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(107, 6, 4, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(108, 6, 4, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(109, 6, 5, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(110, 6, 5, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(111, 6, 1, '组织某某电影的见面会', 6600, 6840, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(112, 6, 1, '组织某某书籍签售会', 5160, 5400, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(113, 6, 1, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(114, 6, 2, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(115, 6, 2, '活动室物品清点', 9780, 9900, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(116, 6, 2, '日常部门文案整理', 9240, 9360, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(117, 6, 3, '前往学校领导汇报赞助情况', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(118, 6, 3, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(119, 6, 3, '前往新东方某某公司拉取赞助', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(120, 6, 4, '前往学校领导汇报比赛情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10','false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(121, 6, 4, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(122, 6, 4, '举办3v3趣味篮球赛', 9780, 9900, '篮球馆', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(123, 6, 5, '前往学校领导汇报情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(124, 6, 5, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(125, 6, 5, '学校某某讲座采访记录', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        //第7周
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(126, 7, 1, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(127, 7, 1, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(128, 7, 2, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(129, 7, 2, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(130, 7, 3, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(131, 7, 3, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(132, 7, 4, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(133, 7, 4, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(134, 7, 5, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(135, 7, 5, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(136, 7, 1, '打扫活动室', 2280, 2460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(137, 7, 1, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(138, 7, 1, '组织校十佳歌手比赛', 9780, 9900, '青春广场', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(139, 7, 2, '打扫活动室', 2280, 2460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(140, 7, 2, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(141, 7, 2, '活动室物品清点',9780, 9900, '活动室',     '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(142, 7, 3, '打扫活动室', 2280, 2460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(143, 7, 3, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(144, 7, 3, '前往新东方某某公司拉取赞助', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(145, 7, 4, '打扫活动室', 2280, 2460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(146, 7, 4, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(147, 7, 4, '举办3v3趣味篮球赛', 9780, 9900, '篮球馆', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(148, 7, 5, '打扫活动室', 2280, 2460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(149, 7, 5, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(150, 7, 5, '学校某某讲座采访记录', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        //第8周
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(151, 8, 1, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(152, 8, 1, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(153, 8, 2, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(154, 8, 2, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(155, 8, 3, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(156, 8, 3, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(157, 8, 4, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(158, 8, 4, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(159, 8, 5, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(160, 8, 5, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(161, 8, 1, '组织校迎新晚会', 8340, 8460, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(162, 8, 1, '组织某某电影的见面会', 6600, 6840, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(163, 8, 1, '百团纳新服务人员', 9240, 9360, '青春广场', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(164, 8, 2, '活动室借用管理', 8340, 8460, '活动室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(165, 8, 2, '团委消息传达', 6600, 6840, '活动室',     '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(166, 8, 2, '日常部门文案整理', 9240, 9360, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(167, 8, 3, '前往星网锐捷某某公司拉取赞助', 8340, 8460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(168, 8, 3, '赞助要求条件完成（宣传展板、拱门等）', 9240, 9360, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(169, 8, 3, '赞助要求条件完成（举办宣讲会等）', 6600, 6840, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(170, 8, 4, '篮球校联赛经管vs土木比赛记录', 8340, 8460, '篮球馆', '0.1', '10','false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(171, 8, 4, '篮球校联赛数计vs电气比赛记录', 6600, 6840, '篮球馆', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(172, 8, 4, '举办校友会友谊赛', 9240, 9360, '篮球馆', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(173, 8, 5, '学生会宣传栏信息更新', 8340, 8460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(174, 8, 5, '学校某某会议录影录像', 6600, 6840, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(175, 8, 5, '学校公众号信息更新', 9240, 9360, '素拓', '0.1', '10', 'false')");
        //第9周
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(176, 9, 1, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(177, 9, 1, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(178, 9, 2, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(179, 9, 2, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(180, 9, 3, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(181, 9, 3, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(182, 9, 4, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(183, 9, 4, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(184, 9, 5, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(185, 9, 5, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(186, 9, 1, '组织晚会', 4020, 4140, '青春广场', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(187, 9, 1, '组织某某书籍签售会', 5160, 5400, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(188, 9, 1, '百团纳新服务人员', 9240, 9360, '青春广场', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(189, 9, 2, '道具购买报销', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(190, 9, 2, '道具（展板，红旗等）管理配发', 5160, 5400, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(191, 9, 2, '日常部门文案整理', 9240, 9360, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(192, 9, 3, '前往学校领导汇报赞助情况', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(193, 9, 3, '前往某某电影院拉取赞助', 5160, 5400, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(194, 9, 3, '赞助要求条件完成（举办宣讲会等）', 6600, 6840, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(195, 9, 4, '前往学校领导汇报比赛情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10','false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(196, 9, 4, '篮球校联赛数计vs电气比赛记录', 6600, 6840, '篮球馆', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(197, 9, 4, '篮球校联赛法学vs化学比赛记录', 5160, 5400, '篮球馆', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(198, 9, 5, '前往学校领导汇报情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(199, 9, 5, '学校微博信息更新', 5160, 5400, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(200, 9, 5, '学校公众号信息更新', 9240, 9360, '素拓', '0.1',  '10', 'false')");
        //第10周
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(201, 10, 1, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(202, 10, 1, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(203, 10, 2, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(204, 10, 2, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(205, 10, 3, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(206, 10, 3, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(207, 10, 4, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(208, 10, 4, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(209, 10, 5, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(210, 10, 5, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(211, 10, 1, '组织某某电影的见面会', 6600, 6840, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(212, 10, 1, '组织某某书籍签售会', 5160, 5400, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(213, 10, 1, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(214, 10, 2, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(215, 10, 2, '活动室物品清点', 9780, 9900, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(216, 10, 2, '日常部门文案整理', 9240, 9360, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(217, 10, 3, '前往学校领导汇报赞助情况', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(218, 10, 3, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(219, 10, 3, '前往新东方某某公司拉取赞助', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(220, 10, 4, '前往学校领导汇报比赛情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10','false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(221, 10, 4, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(222, 10, 4, '举办3v3趣味篮球赛', 9780, 9900, '篮球馆', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(223, 10, 5, '前往学校领导汇报情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(224, 10, 5, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(225, 10, 5, '学校某某讲座采访记录', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        //第11周
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(226, 11, 1, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(227, 11, 1, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(228, 11, 2, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(229, 11, 2, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(230, 11, 3, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(231, 11, 3, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(232, 11, 4, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(233, 11, 4, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(234, 11, 5, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(235, 11, 5, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(236, 11, 1, '组织某某电影的见面会', 6600, 6840, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(237, 11, 1, '组织某某书籍签售会', 5160, 5400, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(238, 11, 1, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(239, 11, 2, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(240, 11, 2, '活动室物品清点', 9780, 9900, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(241, 11, 2, '日常部门文案整理', 9240, 9360, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(242, 11, 3, '前往学校领导汇报赞助情况', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(243, 11, 3, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(244, 11, 3, '前往新东方某某公司拉取赞助', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(245, 11, 4, '前往学校领导汇报比赛情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10','false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(246, 11, 4, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(247, 11, 4, '举办3v3趣味篮球赛', 9780, 9900, '篮球馆', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(248, 11, 5, '前往学校领导汇报情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(249, 11, 5, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(250, 11, 5, '学校某某讲座采访记录', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        //第12周
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(251, 12, 1, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(252, 12, 1, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(253, 12, 2, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(254, 12, 2, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(255, 12, 3, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(256, 12, 3, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(257, 12, 4, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(258, 12, 4, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(259, 12, 5, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(260, 12, 5, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(261, 12, 1, '打扫活动室', 2280, 2460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(262, 12, 1, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(263, 12, 1, '组织校十佳歌手比赛', 9780, 9900, '青春广场', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(264, 12, 2, '打扫活动室', 2280, 2460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(265, 12, 2, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(266, 12, 2, '活动室物品清点',9780, 9900, '活动室',     '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(267, 12, 3, '打扫活动室', 2280, 2460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(268, 12, 3, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(269, 12, 3, '前往新东方某某公司拉取赞助', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(270, 12, 4, '打扫活动室', 2280, 2460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(271, 12, 4, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(272, 12, 4, '举办3v3趣味篮球赛', 9780, 9900, '篮球馆', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(273, 12, 5, '打扫活动室', 2280, 2460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(274, 12, 5, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(275, 12, 5, '学校某某讲座采访记录', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        //第13周
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(276, 13, 1, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(277, 13, 1, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(278, 13, 2, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(279, 13, 2, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(280, 13, 3, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(281, 13, 3, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(282, 13, 4, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(283, 13, 4, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(284, 13, 5, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(285, 13, 5, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(286, 13, 1, '组织校迎新晚会', 8340, 8460, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(287, 13, 1, '组织某某电影的见面会', 6600, 6840, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(288, 13, 1, '百团纳新服务人员', 9240, 9360, '青春广场', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(289, 13, 2, '活动室借用管理', 8340, 8460, '活动室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(290, 13, 2, '团委消息传达', 6600, 6840, '活动室',     '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(291, 13, 2, '日常部门文案整理', 9240, 9360, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(292, 13, 3, '前往星网锐捷某某公司拉取赞助', 8340, 8460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(293, 13, 3, '赞助要求条件完成（宣传展板、拱门等）', 9240, 9360, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(294, 13, 3, '赞助要求条件完成（举办宣讲会等）', 6600, 6840, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(295, 13, 4, '篮球校联赛经管vs土木比赛记录', 8340, 8460, '篮球馆', '0.1', '10','false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(296, 13, 4, '篮球校联赛数计vs电气比赛记录', 6600, 6840, '篮球馆', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(297, 13, 4, '举办校友会友谊赛', 9240, 9360, '篮球馆', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(298, 13, 5, '学生会宣传栏信息更新', 8340, 8460, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(299, 13, 5, '学校某某会议录影录像', 6600, 6840, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(300, 13, 5, '学校公众号信息更新', 9240, 9360, '素拓', '0.1', '10', 'false')");
        //第14周
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(301, 14, 1, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(302, 14, 1, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(303, 14, 2, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(304, 14, 2, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(305, 14, 3, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(306, 14, 3, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(307, 14, 4, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(308, 14, 4, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(309, 14, 5, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(310, 14, 5, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(311, 14, 1, '组织晚会', 4020, 4140, '青春广场', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(312, 14, 1, '组织某某书籍签售会', 5160, 5400, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(313, 14, 1, '百团纳新服务人员', 9240, 9360, '青春广场', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(314, 14, 2, '道具购买报销', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(315, 14, 2, '道具（展板，红旗等）管理配发', 5160, 5400, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(316, 14, 2, '日常部门文案整理', 9240, 9360, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(317, 14, 3, '前往学校领导汇报赞助情况', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(318, 14, 3, '前往某某电影院拉取赞助', 5160, 5400, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(319, 14, 3, '赞助要求条件完成（举办宣讲会等）', 6600, 6840, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(320, 14, 4, '前往学校领导汇报比赛情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10','false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(321, 14, 4, '篮球校联赛数计vs电气比赛记录', 6600, 6840, '篮球馆', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(322, 14, 4, '篮球校联赛法学vs化学比赛记录', 5160, 5400, '篮球馆', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(323, 14, 5, '前往学校领导汇报情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(324, 14, 5, '学校微博信息更新', 5160, 5400, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(325, 14, 5, '学校公众号信息更新', 9240, 9360, '素拓', '0.1',  '10', 'false')");
        //第15周
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(326, 15, 1, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(327, 15, 1, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(328, 15, 2, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(329, 15, 2, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(330, 15, 3, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(331, 15, 3, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(332, 15, 4, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(333, 15, 4, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(334, 15, 5, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(335, 15, 5, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(336, 15, 1, '组织某某电影的见面会', 6600, 6840, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(337, 15, 1, '组织某某书籍签售会', 5160, 5400, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(338, 15, 1, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(339, 15, 2, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(340, 15, 2, '活动室物品清点', 9780, 9900, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(341, 15, 2, '日常部门文案整理', 9240, 9360, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(342, 15, 3, '前往学校领导汇报赞助情况', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(343, 15, 3, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(344, 15, 3, '前往新东方某某公司拉取赞助', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(345, 15, 4, '前往学校领导汇报比赛情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10','false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(346, 15, 4, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(347, 15, 4, '举办3v3趣味篮球赛', 9780, 9900, '篮球馆', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(348, 15, 5, '前往学校领导汇报情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(349, 15, 5, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(350, 15, 5, '学校某某讲座采访记录', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        //第16周
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(351, 16, 1, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(352, 16, 1, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(353, 16, 2, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(354, 16, 2, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(355, 16, 3, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(356, 16, 3, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(357, 16, 4, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(358, 16, 4, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(359, 16, 5, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(360, 16, 5, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(361, 16, 1, '组织某某电影的见面会', 6600, 6840, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(362, 16, 1, '组织某某书籍签售会', 5160, 5400, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(363, 16, 1, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(364, 16, 2, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(365, 16, 2, '活动室物品清点', 9780, 9900, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(366, 16, 2, '日常部门文案整理', 9240, 9360, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(367, 16, 3, '前往学校领导汇报赞助情况', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(368, 16, 3, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(369, 16, 3, '前往新东方某某公司拉取赞助', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(370, 16, 4, '前往学校领导汇报比赛情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10','false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(371, 16, 4, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(372, 16, 4, '举办3v3趣味篮球赛', 9780, 9900, '篮球馆', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(373, 16, 5, '前往学校领导汇报情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(374, 16, 5, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(375, 16, 5, '学校某某讲座采访记录', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        //第17周
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(376, 17, 1, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(377, 17, 1, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(378, 17, 2, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(379, 17, 2, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(380, 17, 3, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(381, 17, 3, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(382, 17, 4, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(383, 17, 4, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(384, 17, 5, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(385, 17, 5, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(386, 17, 1, '组织某某电影的见面会', 6600, 6840, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(387, 17, 1, '组织某某书籍签售会', 5160, 5400, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(388, 17, 1, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(389, 17, 2, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(390, 17, 2, '活动室物品清点', 9780, 9900, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(391, 17, 2, '日常部门文案整理', 9240, 9360, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(392, 17, 3, '前往学校领导汇报赞助情况', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(393, 17, 3, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(394, 17, 3, '前往新东方某某公司拉取赞助', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(395, 17, 4, '前往学校领导汇报比赛情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10','false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(396, 17, 4, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(397, 17, 4, '举办3v3趣味篮球赛', 9780, 9900, '篮球馆', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(398, 17, 5, '前往学校领导汇报情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(399, 17, 5, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(400, 17, 5, '学校某某讲座采访记录', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        //第18周
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(401, 18, 1, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(402, 18, 1, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(403, 18, 2, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(404, 18, 2, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(405, 18, 3, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(406, 18, 3, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(407, 18, 4, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(408, 18, 4, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(409, 18, 5, '部门例会', 1140, 1260, '素拓', '0.2',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(410, 18, 5, '值班',      480,  720, '素拓', '0.15', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(411, 18, 1, '组织某某电影的见面会', 6600, 6840, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(412, 18, 1, '组织某某书籍签售会', 5160, 5400, '青春广场', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(413, 18, 1, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(414, 18, 2, '部门联谊', 4020, 4140, '素拓', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(415, 18, 2, '活动室物品清点', 9780, 9900, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(416, 18, 2, '日常部门文案整理', 9240, 9360, '活动室', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(417, 18, 3, '前往学校领导汇报赞助情况', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(418, 18, 3, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(419, 18, 3, '前往新东方某某公司拉取赞助', 9780, 9900, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(420, 18, 4, '前往学校领导汇报比赛情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10','false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(421, 18, 4, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(422, 18, 4, '举办3v3趣味篮球赛', 9780, 9900, '篮球馆', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(423, 18, 5, '前往学校领导汇报情况及申请资金', 3720, 3960, '辅导员办公室', '0.1', '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(424, 18, 5, '部门联谊', 4020, 4140, '素拓', '0.1',  '10', 'false')");
        sQLiteDatabase.execSQL("insert into DepartmentActivities values(425, 18, 5, '学校某某讲座采访记录', 9780, 9900, '素拓', '0.1',  '10', 'false')");

        /************** 创建问题表 **************************/
        String createQuestionsSql = "create table Questions( " +
                " Qid                 int not null," +       //问题id
                " QNo                 int," +                //问题编号
                " Cid                 int," +                //问题是哪个课程的
                " QIsAnswer           varchar(20)," +        //问题是否已经回答过了
                " primary key (Qid)" +
                " );";
        sQLiteDatabase.execSQL(createQuestionsSql);

        /*********** 往问题表插入数据 *********************/
        sQLiteDatabase.execSQL("insert into Questions values( 1, 1, 1,'false')");
        sQLiteDatabase.execSQL("insert into Questions values( 2, 2, 1,'false')");
        sQLiteDatabase.execSQL("insert into Questions values( 3, 3, 1,'false')");
        sQLiteDatabase.execSQL("insert into Questions values( 4, 4, 1,'false')");
        sQLiteDatabase.execSQL("insert into Questions values( 5, 5, 2,'false')");
        sQLiteDatabase.execSQL("insert into Questions values( 6, 6, 2,'false')");
        sQLiteDatabase.execSQL("insert into Questions values( 7, 7, 2,'false')");
        sQLiteDatabase.execSQL("insert into Questions values( 8, 8, 2,'false')");
        sQLiteDatabase.execSQL("insert into Questions values( 9, 9, 2,'false')");

}


    //软件版本号发生改变时调用
    @Override
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int oldVersion, int newVersion) {


    }
}
