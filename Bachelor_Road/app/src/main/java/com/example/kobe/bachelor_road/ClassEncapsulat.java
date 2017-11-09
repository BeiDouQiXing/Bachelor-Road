package com.example.kobe.bachelor_road;

/**
 *
 * 人物类，部门类，课程类
 * Created by Administrator on 2017/11/6.
 */

public class ClassEncapsulat {

}

class Character{
    private static int CHid;
    private static String CHName;
    private static String CHNo;
    private static int CHClass;
    private static int CHImage;
    private static int CHCurrentEnergy;
    private static int CHMaximumEnergy;
    private static float CHCredit;
    private static float CHComprehensiveTest;
    private static int CHCurrentTime;      //从周一算起当前时间，以分钟计？
    private static int CHCurrentWeek;       //当前第几周
    private static boolean CHIsFirstLogin;

    public Character(){

    }

    public Character(int CHid,String CHName,String CHNo,int CHClass,int CHImage,int CHCurrentEnergy,int CHMaximumEnergy,
                     float CHCredit,float CHComprehensiveTest,int CHCurrentTime,int CHCurrentWeek,boolean CHIsFirstLogin){
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
        this.CHIsFirstLogin = CHIsFirstLogin;
    }

    public void setCharacter(int CHid,String CHName,String CHNo,int CHClass,int CHImage,int CHCurrentEnergy,int CHMaximumEnergy,
                             float CHCredit,float CHComprehensiveTest,int CHCurrentTime,int CHCurrentWeek,boolean CHIsFirstLogin){
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
        this.CHIsFirstLogin = CHIsFirstLogin;
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

    public  void setCHCredit(float CHCredit){
        this.CHCredit = CHCredit;
    }

    public  float getCHCredit(){
        return CHCredit;
    }

    public  void setCHComprehensiveTest(float CHComprehensiveTest){
        this.CHComprehensiveTest = CHComprehensiveTest;
    }

    public  float getCHComprehensiveTest(){
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

    public  void setCHIsFirstLogin(boolean CHIsFirstLogin){
        this.CHIsFirstLogin = CHIsFirstLogin;
    }

    public  boolean getCHIsFirstLogin(){
        return CHIsFirstLogin;
    }

}