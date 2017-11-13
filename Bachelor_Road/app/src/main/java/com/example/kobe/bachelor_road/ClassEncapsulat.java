package com.example.kobe.bachelor_road;

/**
 * Created by Administrator on 2017/11/6.
 */

public class ClassEncapsulat {

}

class Character{
    private int CHid;
    private String CHName;
    private String CHNo;
    private int CHClass;
    private int CHImage;
    private int CHCurrentEnergy;
    private int CHMaximumEnergy;
    private double CHCredit;
    private double CHComprehensiveTest;
    private int CHCurrentTime;      //从周一算起当前时间，以分钟计？
    private int CHCurrentWeek;       //当前第几周

    public Character(){

    }

    public Character(int CHid,String CHName,String CHNo,int CHClass,int CHImage,int CHCurrentEnergy,int CHMaximumEnergy,
                     double CHCredit,double CHComprehensiveTest,int CHCurrentTime,int CHCurrentWeek){
        this.CHid = CHid;
        this.CHName = CHName;
        this.CHNo  = CHNo;
        this.CHClass = CHClass;
        this.CHImage = CHImage;
        this.CHCurrentEnergy = CHCurrentEnergy;
        this.CHMaximumEnergy = CHMaximumEnergy;
        this.CHCredit = CHCredit;
        this.CHComprehensiveTest = CHComprehensiveTest;
        this.CHCurrentTime = CHCurrentTime;
        this.CHCurrentWeek = CHCurrentWeek;
    }

    public void setCharacter(int CHid,String CHName,String CHNo,int CHClass,int CHImage,int CHCurrentEnergy,int CHMaximumEnergy,
                             double CHCredit,double CHComprehensiveTest,int CHCurrentTime,int CHCurrentWeek){
        this.CHid = CHid;
        this.CHName = CHName;
        this.CHNo  = CHNo;
        this.CHClass = CHClass;
        this.CHImage = CHImage;
        this.CHCurrentEnergy = CHCurrentEnergy;
        this.CHMaximumEnergy = CHMaximumEnergy;
        this.CHCredit = CHCredit;
        this.CHComprehensiveTest = CHComprehensiveTest;
        this.CHCurrentTime = CHCurrentTime;
        this.CHCurrentWeek = CHCurrentWeek;
    }

    public int getCHid(){
        return CHid;
    }

    public  String getCHName(){
        return CHName;
    }

    public  String getCHNo(){
        return CHNo;
    }

    public  int getCHClass(){
        return CHClass;
    }

    public  int getCHImage(){
        return CHImage;
    }

    public  int getCHCurrentEnergy(){
        return CHCurrentEnergy;
    }

    public  int getCHMaximumEnergy(){
        return CHMaximumEnergy;
    }

    public  void setCHCredit(double CHCredit){
        this.CHCredit = CHCredit;
    }

    public  double getCHCredit(){
        return CHCredit;
    }

    public  void setCHComprehensiveTest(double CHComprehensiveTest){
        this.CHComprehensiveTest = CHComprehensiveTest;
    }

    public  double getCHComprehensiveTest(){
        return CHComprehensiveTest;
    }

    public  void setCHCurrentTime(int CHCurrentTime){
        this.CHCurrentTime = CHCurrentTime;
    }

    public  int getCHCurrentTime(){
        return CHCurrentTime;
    }

    public  void setCHCurrentWeek(int CHCurrentWeek){
        this.CHCurrentWeek = CHCurrentWeek;
    }

    public  int getCHCurrentWeek(){
        return CHCurrentWeek;
    }
}

class Course{
    private int Cid;    //课程号
    private String CName;       //课程名
    private int CEachClassEnergy;   //每节课消耗多少活力值，int型
    private double CCredit;       //课程总学分
    private double CEachClassCredit;  //每节课获得多少学分，double型

    public Course(){

    }

    public Course(int id, String Name, int EachClassEnergy,double Credit,double  EachClassCredit){
        Cid = id;
        CName = Name;
        CEachClassEnergy = EachClassEnergy;
        CCredit = Credit;
        CEachClassCredit = EachClassCredit;
    }

    public void setCourse(int id, String Name, int EachClassEnergy,double Credit,double  EachClassCredit){
        Cid = id;
        CName = Name;
        CEachClassEnergy = EachClassEnergy;
        CCredit = Credit;
        CEachClassCredit = EachClassCredit;
    }

    public int getCid(){
        return Cid;
    }

    public String getCName(){
        return CName;
    }

    public int getCEachClassEnergy(){
        return CEachClassEnergy;
    }

    public double getCCredit(){
        return CCredit;
    }

    public double getCEachClassCredit(){
        return CEachClassCredit;
    }

}


class CharacterCourse{
    int CHCid;
    int CHCWeek;
    String CName;
    int CHCSchooltimeWeek;
    int CHCSchooltimeClass;
    String CHCClassLocationBuild;
    String CHCClassLocationClass;
    boolean CHCIsAttendClass;
}

