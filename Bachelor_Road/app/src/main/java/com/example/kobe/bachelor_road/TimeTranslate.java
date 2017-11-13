package com.example.kobe.bachelor_road;

/**
 * Created by 溟 on 2017/11/14.
 */

public class TimeTranslate {
    public static String timeIntToString(int CHCurrentTime) {
        int weekInt=(CHCurrentTime/60)/24+1;
        String weekString = null;
        if(weekInt==1)
            weekString="周一 ";
        else if(weekInt==2)
            weekString="周二 ";
        else if(weekInt==3)
            weekString="周三 ";
        else if(weekInt==4)
            weekString="周四 ";
        else if(weekInt==5)
            weekString="周五 ";
        else if(weekInt==6)
            weekString="周六 ";
        else if(weekInt==7)
            weekString="周日 ";
        String hourString;
        int hourInt=CHCurrentTime/60-(weekInt-1)*24;
        String a = String.valueOf(hourInt);
        if(hourInt>9)
            hourString=a+":";
        else
            hourString="0"+a+":";
        int minuteInt=CHCurrentTime-((weekInt-1)*24+hourInt)*60;
        String minuteString;
        String b = String.valueOf(minuteInt);
        if(minuteInt>9)
            minuteString=b;
        else
            minuteString="0"+b;
        String timeString = weekString + hourString + minuteString;
        return timeString;
    }

    public static String morningOrAfter(int CHCurrentTime) {
        int weekInt=(CHCurrentTime/60)/24+1;
        String hourString=null;
        int hourInt=CHCurrentTime/60-(weekInt-1)*24;
        if(hourInt>11)
            hourString="下午";
        else
            hourString="上午";
        return hourString;
    }

    public static int timeStringToInt(String time) {
        String weekString = time.substring(0, 2);
        int weekInt=0;
        if(weekString.equals("周一"))
            weekInt=0;
        else if(weekString.equals("周二"))
            weekInt=1;
        else if(weekString.equals("周三"))
            weekInt=2;
        else if(weekString.equals("周四"))
            weekInt=3;
        else if(weekString.equals("周五"))
            weekInt=4;
        else if(weekString.equals("周六"))
            weekInt=5;
        else if(weekString.equals("周日"))
            weekInt=6;
        String hourString1 = time.substring(3,4);
        String hourString2 = time.substring(4,5);
        int a = Integer.valueOf(hourString1);
        int b = Integer.valueOf(hourString2);
        int hourInt=a*10+b;
        String minuteString1 = time.substring(6,7);
        String minuteString2 = time.substring(7,8);
        int c = Integer.valueOf(minuteString1);
        int d = Integer.valueOf(minuteString2);
        int minuteInt=c*10+d;
        int timeInt=weekInt*24*60+hourInt*60+minuteInt;
        return timeInt;
    }
}
