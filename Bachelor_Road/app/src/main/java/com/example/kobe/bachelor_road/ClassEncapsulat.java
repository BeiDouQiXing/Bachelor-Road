package com.example.kobe.bachelor_road;

/**
 * Created by Administrator on 2017/11/6.
 */

public class ClassEncapsulat {

}

/***************** 人物类***************/
class Character{
    public int    CHid;
    public String CHName;		//姓名
    public String CHNo;			//学号
    public int    CHClass;		//班级
    public String CHGender;    //性别
    public int    CHCurrentEnergy;		//活力值，int型
    public int 	  CHMaximumEnergy;		//活力值上限，int型
    public double CHCredit;				//学分，double型
    public double CHComprehensiveTest;	//综测，double型
    public int 	  CHCurrentTime;      	//从周一算起当前时间，以分钟计，int型
    public int 	  CHCurrentWeek;       	//当前第几周，int型
    public byte[] CHCImagebyte;         //头像
    public Character(){

    }

    public Character(int CHid, String CHName, String CHNo, int CHClass, String CHGender,int CHCurrentEnergy,int CHMaximumEnergy,
                     double CHCredit,double CHComprehensiveTest,int CHCurrentTime,int CHCurrentWeek, byte[] CHCImagebyte ){
        this.CHid = CHid;
        this.CHName = CHName;
        this.CHNo  = CHNo;
        this.CHClass = CHClass;
        this.CHGender = CHGender;
        this.CHCurrentEnergy = CHCurrentEnergy;
        this.CHMaximumEnergy = CHMaximumEnergy;
        this.CHCredit = CHCredit;
        this.CHComprehensiveTest = CHComprehensiveTest;
        this.CHCurrentTime = CHCurrentTime;
        this.CHCurrentWeek = CHCurrentWeek;
        this.CHCImagebyte = CHCImagebyte;
    }
}

/************************ 课程类**********************/
class Course{
    public int Cid;    //课程号
    public String CName;       //课程名
    public int CEachClassEnergy;   //每节课消耗多少活力值，int型
    public double CCredit;       //课程总学分
    public double CEachClassCredit;  //每节课获得多少学分，double型

    public Course(){

    }
}

/******************** 功课表类*****************/
class CharacterCourse{
    public int CHCid;	//本节课id
    public int CHCWeek;		//本节课第几周
    public int Cid;			//本节课课程号
    public String CName;	//本节课课程名
    public int CHCSchooltimeWeek;	//周几上课，int型
    public int CHCSchooltimeClass;	//当天第几节的课，int型
    public String CHCClassLocation;	//上课地点
    public boolean CHCIsAttendClass; //是否有参加本节课

    public  CharacterCourse(){

    }
}

/********************* 部门类********************/
class Department{
    public int Did;     //部门id
    public String DName;    //部门名称
    public String DFunctions;   //部门职能
    public boolean DIsJoinDepartment;   //是否加入部门

    public Department(){

    }
}


/****************** 部门活动类****************/
class DepartmentActivities{
    public int DAid;    //部门活动id
    public int DAWeek;      //该活动第几周
    public int Did;         // 活动发起部门id
    public String DName;   // 活动发起部门名称
    public String DAName;   //活动名称
    public int DABeginTime; // 活动开始时间
    public int DAEndTime;  //活动结束时间
    public String DALocation; //部门活动地点
    public double DAComprehensiveTest;  //参加本次活动可获得综测
    public int DAEnergy;  //参加活动消耗的活力
    public boolean DAIsJoinActivity;  //是否有参加本次活动

    public DepartmentActivities(){

    }

}


/****************** 问题类****************/
class Questions{
    public int Qid;             //问题id
    public int QNo;             //问题编号
    public int Cid;             //问题是哪个课程的
    public boolean QIsAnswer;     //问题是否回答过了

    public Questions(){

    }
}