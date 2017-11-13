package com.example.kobe.bachelor_road;

/**
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

    /************对 Character 表的操作***********************/
    //插入一条人物记录
    public long insertCharacter(Character character){
        SQLiteDatabase sQLiteDatabase = database.getReadableDatabase(); //以读写方式打开数据库
        Cursor cursor = sQLiteDatabase.query("Character",null,null,null,null,null,null);//查询并获得游标
        long results = 0;
        if( cursor.getCount() > 0 )  //已经注册过了
        {
            results = 0;
        }
        else
        {
            ContentValues values = new ContentValues();// 开始组装数据
            values.clear();
            values.put("CHid", character.CHid);
            values.put("CHName", character.CHName);
            values.put("CHNo", character.CHNo);
            values.put("CHClass", character.CHClass);
            values.put("CHImage", character.CHImage);
            values.put("CHCurrentEnergy", String.valueOf(character.CHCurrentEnergy) );
            values.put("CHMaximumEnergy", String.valueOf(character.CHMaximumEnergy) );
            values.put("CHCredit", String.valueOf(character.CHCredit) );
            values.put("CHComprehensiveTest", String.valueOf(character.CHComprehensiveTest) );
            values.put("CHCurrentTime", String.valueOf(character.CHCurrentTime));
            values.put("CHCurrentWeek", character.CHCurrentWeek);
            results = sQLiteDatabase.insert("Character", null, values);
        }
        sQLiteDatabase.close();
        return results;
    }

    //查询人物全属性
    public Character queryCharacter(){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase(); //以读写方式打开数据库
        Cursor cursor = sQLiteDatabase.query("Character",null,null,null,null,null,null);//查询并获得游标
        Character character = new Character();
        if(cursor.moveToNext()) {
            character.CHid = cursor.getInt(cursor.getColumnIndex("CHid"));
            character.CHName = cursor.getString(cursor.getColumnIndex("CHName"));
            character.CHNo = cursor.getString(cursor.getColumnIndex("CHNo"));
            character.CHClass = cursor.getInt(cursor.getColumnIndex("CHClass"));
            character.CHImage = cursor.getInt(cursor.getColumnIndex("CHImage"));
            character.CHCurrentEnergy = Integer.valueOf(cursor.getString(cursor.getColumnIndex("CHCurrentEnergy")));
            character.CHMaximumEnergy = Integer.valueOf(cursor.getString(cursor.getColumnIndex("CHMaximumEnergy")));
            character.CHCredit = Double.parseDouble(cursor.getString(cursor.getColumnIndex("CHCredit")));
            character.CHComprehensiveTest =  Double.parseDouble(cursor.getString(cursor.getColumnIndex("CHComprehensiveTest")));
            character.CHCurrentTime = cursor.getInt(cursor.getColumnIndex("CHCurrentTime"));
            character.CHCurrentWeek = cursor.getInt(cursor.getColumnIndex("CHCurrentWeek"));
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
    public int queryCHCurrentEnergy(){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase(); //以读写方式打开数据库
        Cursor cursor = sQLiteDatabase.query("Character",null,null,null,null,null,null);//查询并获得游标
        int CHCurrentEnergy = -1;
        if(cursor.moveToNext()) {
            CHCurrentEnergy = Integer.valueOf(cursor.getString(cursor.getColumnIndex("CHCurrentEnergy")));
        }
        sQLiteDatabase.close();
        return CHCurrentEnergy;
    }

    //查询学分
    public double queryCHCredit(){
        SQLiteDatabase sQLiteDatabase = database.getReadableDatabase(); //以读写方式打开数据库
        Cursor cursor = sQLiteDatabase.query("Character",null,null,null,null,null,null);//查询并获得游标
        double CHCredit = -1;
        if(cursor.moveToNext()) {
            CHCredit = Double.valueOf(cursor.getString(cursor.getColumnIndex("CHCredit")));
        }
        sQLiteDatabase.close();
        return CHCredit;
    }

    //查询综测
    public double queryCHComprehensiveTest(){
        SQLiteDatabase sQLiteDatabase = database.getReadableDatabase(); //以读写方式打开数据库
        Cursor cursor = sQLiteDatabase.query("Character",null,null,null,null,null,null);//查询并获得游标
        double CHComprehensiveTest = 0;
        if(cursor.moveToNext()) {
            CHComprehensiveTest = Double.valueOf(cursor.getString(cursor.getColumnIndex("CHComprehensiveTest")));
        }
        sQLiteDatabase.close();
        return CHComprehensiveTest;
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
    public int updateCHCurrentEnergy(int CHCurrentEnergy){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase(); //以读写方式打开数据库
        ContentValues values = new ContentValues();
        values.clear();
        values.put("CHCurrentEnergy", String.valueOf(CHCurrentEnergy));
        int results = sQLiteDatabase.update("Character", values, null, null) ; //失败的返回值小于0
        sQLiteDatabase.close();
        return results;
    }

    //修改活力值上限
    public int updateCHMaximumEnergy(int CHMaximumEnergy){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase(); //以读写方式打开数据库
        ContentValues values = new ContentValues();
        values.clear();
        values.put("CHMaximumEnergy", String.valueOf(CHMaximumEnergy));
        int results = sQLiteDatabase.update("Character", values, null, null);
        sQLiteDatabase.close();
        return results;
    }

    //修改学分
    public int updateCHCredit(double CHCredit){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase(); //以读写方式打开数据库
        ContentValues values = new ContentValues();
        values.clear();
        values.put("CHCredit", String.valueOf(CHCredit));
        int results = sQLiteDatabase.update("Character", values, null, null);
        sQLiteDatabase.close();
        return results;
    }

    //修改综测
    public int updateCHComprehensiveTest(double CHComprehensiveTest){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase(); //以读写方式打开数据库
        ContentValues values = new ContentValues();
        values.clear();
        values.put("CHComprehensiveTest", String.valueOf(CHComprehensiveTest));
        int results = sQLiteDatabase.update("Character", values, null, null);
        sQLiteDatabase.close();
        return results;
    }

    //修改当前时间
    public int updateCHCurrentTime(int CHCurrentTime){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.clear();
        values.put("CHCurrentTime", CHCurrentTime);
        int results = sQLiteDatabase.update("Character", values, null, null);
        sQLiteDatabase.close();
        return results;
    }

    //修改当前周
    public int updateCHCurrentWeek(int CHCurrentWeek){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.clear();
        values.put("CHCurrentWeek", CHCurrentWeek);
        int results = sQLiteDatabase.update("Character", values, null, null);
        sQLiteDatabase.close();
        return results;
    }

    /********对 Course 表的操作 *********************/
    //查询所有课程的全属性
    public Course[] queryAllCourse(){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        Cursor cursor = sQLiteDatabase.query("Course",null,null,null,null,null,null);//查询并获得游标
        Course[] course = new Course[cursor.getCount()];
        for (int i=0; cursor.moveToNext() == true; i++ ){
            course[i] = new Course();
            course[i].Cid= cursor.getInt(cursor.getColumnIndex("Cid"));
            course[i].CName = cursor.getString(cursor.getColumnIndex("CName"));
            course[i].CEachClassEnergy = Integer.valueOf(cursor.getString(cursor.getColumnIndex("CEachClassEnergy")));
            course[i].CCredit = Double.valueOf(cursor.getString(cursor.getColumnIndex("CCredit")));
            course[i].CEachClassCredit = Double.valueOf(cursor.getString(cursor.getColumnIndex("CEachClassCredit")));
        }
        sQLiteDatabase.close();
        return course;
    }

   //查询指定课程号的学分
    public double queryCidCCredit(int Cid){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery("select CCredit from Course where Cid=?",new String[]{String.valueOf(Cid)});
        double CCredit = -1;
        if( cursor.moveToNext() == true  )
            CCredit = Double.valueOf(cursor.getString(cursor.getColumnIndex("CCredit")));
        sQLiteDatabase.close();
        return CCredit;
    }

    //查询指定课程名的学分
    public double queryCNameCCredit(String CName){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery("select CCredit from Course where CName=?",new String[]{CName});
        double CCredit = -1;
        if( cursor.moveToNext() == true  )
            CCredit = Double.valueOf(cursor.getString(cursor.getColumnIndex("CCredit")));
        sQLiteDatabase.close();
        return CCredit;
    }

    //查询指定课程号的每节课消耗活力值
    public int queryCidCEachClassEnergy(int Cid){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery("select CEachClassEnergy from Course where Cid=?",new String[]{String.valueOf(Cid)});
        int CEachClassEnergy = -1;
        if( cursor.moveToNext() == true  )
            CEachClassEnergy = Integer.valueOf(cursor.getString(cursor.getColumnIndex("CEachClassEnergy")));
        sQLiteDatabase.close();
        return CEachClassEnergy;
    }

    //查询指定课程名的每节课消耗活力值
    public int queryCNameCEachClassEnergy(String CName){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery("select CEachClassEnergy from Course where CName=?",new String[]{CName});
        int CEachClassEnergy = -1;
        if( cursor.moveToNext() == true  )
            CEachClassEnergy = Integer.valueOf(cursor.getString(cursor.getColumnIndex("CEachClassEnergy")));
        sQLiteDatabase.close();
        return CEachClassEnergy;
    }

    //查询指定课程号的每节课获得多少学分
    public double queryCidCEachClassCredit(int Cid){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery("select CEachClassCredit from Course where Cid=?",new String[]{String.valueOf(Cid)});
        double CEachClassCredit = -1;
        if( cursor.moveToNext() == true  )
            CEachClassCredit = Double.valueOf(cursor.getString(cursor.getColumnIndex("CEachClassCredit")));
        sQLiteDatabase.close();
        return CEachClassCredit;
    }

    //查询指定课程名的每节课获得多少学分
    public double queryCNameCEachClassCredit(String CName){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery("select CEachClassCredit from Course where CName=?",new String[]{CName});
        double CEachClassCredit = -1;
        if( cursor.moveToNext() == true  )
            CEachClassCredit = Double.valueOf(cursor.getString(cursor.getColumnIndex("CEachClassCredit")));
        sQLiteDatabase.close();
        return CEachClassCredit;
     }

    /*******对 CharacterCourse 表的操作 *******************************/
    // 查询当前周的课程表
    public CharacterCourse[] queryCharacterCourse(int week){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery("select * from CharacterCourse,Course where CharacterCourse.Cid = Course.Cid and CHCWeek=?",new String[]{String.valueOf(week)});
        CharacterCourse[] characterCourse = new CharacterCourse[cursor.getCount()];
        for (int i=0; cursor.moveToNext() == true; i++ ){
            characterCourse[i] = new CharacterCourse();
            characterCourse[i].CHCid = cursor.getInt(cursor.getColumnIndex("CHCid"));
            characterCourse[i].CHCWeek = cursor.getInt(cursor.getColumnIndex("CHCWeek"));
            characterCourse[i].Cid = cursor.getInt(cursor.getColumnIndex("Cid"));
            characterCourse[i].CName = cursor.getString(cursor.getColumnIndex("CName"));
            characterCourse[i].CHCSchooltimeClass = cursor.getInt(cursor.getColumnIndex("CHCSchooltimeClass"));
            characterCourse[i].CHCSchooltimeWeek = cursor.getInt(cursor.getColumnIndex("CHCSchooltimeWeek"));
            characterCourse[i].CHCClassLocation = cursor.getString(cursor.getColumnIndex("CHCClassLocation"));
            String tempCHCIsAttendClass = cursor.getString(cursor.getColumnIndex("CHCIsAttendClass"));
            if(tempCHCIsAttendClass.equalsIgnoreCase("false"))
                characterCourse[i].CHCIsAttendClass = false;
            else if(tempCHCIsAttendClass.equalsIgnoreCase("true"))
                characterCourse[i].CHCIsAttendClass = true;
        }
        sQLiteDatabase.close();
        return characterCourse;
    }

    //查询指定课程号的课程上课时间
    public CharacterCourse queryCHCidCharacterCourse(int CHCid){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery("select * from CharacterCourse,Course where CharacterCourse.Cid = Course.Cid and CHCid=?",new String[]{String.valueOf(CHCid)});
        CharacterCourse characterCourse = new CharacterCourse();
        if( cursor.moveToNext() == true  ) {
            characterCourse.CHCid = cursor.getInt(cursor.getColumnIndex("CHCid"));
            characterCourse.CHCWeek = cursor.getInt(cursor.getColumnIndex("CHCWeek"));
            characterCourse.Cid = cursor.getInt(cursor.getColumnIndex("Cid"));
            characterCourse.CName = cursor.getString(cursor.getColumnIndex("CName"));
            characterCourse.CHCSchooltimeClass = cursor.getInt(cursor.getColumnIndex("CHCSchooltimeClass"));
            characterCourse.CHCSchooltimeWeek = cursor.getInt(cursor.getColumnIndex("CHCSchooltimeWeek"));
            characterCourse.CHCClassLocation = cursor.getString(cursor.getColumnIndex("CHCClassLocation"));
            String tempCHCIsAttendClass = cursor.getString(cursor.getColumnIndex("CHCIsAttendClass"));
            if(tempCHCIsAttendClass.equalsIgnoreCase("false"))
                characterCourse.CHCIsAttendClass = false;
            else if(tempCHCIsAttendClass.equalsIgnoreCase("true"))
                characterCourse.CHCIsAttendClass = true;
        }
        sQLiteDatabase.close();
        return characterCourse;
    }

    // 查询指定的课程号的课程是否有参加
    public int queryCHCidCHCIsAttendClass(int CHCid){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery("select CHCIsAttendClass from CharacterCourse where CHCid=?",new String[]{String.valueOf(CHCid)});
        int CHCIsAttendClass = -1;
        String tempCHCIsAttendClass = "";
        if( cursor.moveToNext() == true  ) {
            tempCHCIsAttendClass = cursor.getString(cursor.getColumnIndex("CHCIsAttendClass"));
            if(tempCHCIsAttendClass.equalsIgnoreCase("false"))
                CHCIsAttendClass = 0;
            else if(tempCHCIsAttendClass.equalsIgnoreCase("true"))
                CHCIsAttendClass = 1;
        }
        sQLiteDatabase.close();
        return CHCIsAttendClass;
    }

    //更新指定课程号的课程是否有参加
    public int updateCHCidCHCIsAttendClass(int CHCid, boolean CHCIsAttendClass){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.clear();
        values.put("CHCIsAttendClass", String.valueOf(CHCIsAttendClass));
        int results = sQLiteDatabase.update("CharacterCourse", values, "CHCid = ?", new String[]{String.valueOf(CHCid)}) ;
        sQLiteDatabase.close();
        return results;
    }

    /********对 Department 表的操作 **************/
    //查询所有部门的全属性
    public Department[] queryAllDepartment(){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery("select * from Department",null);
        Department[] department = new Department[cursor.getCount()];
        for (int i=0; cursor.moveToNext() == true; i++ ){
            department[i] = new Department();
            department[i].Did = cursor.getInt(cursor.getColumnIndex("Did"));
            department[i].DName = cursor.getString(cursor.getColumnIndex("DName"));
            department[i].DFunctions = cursor.getString(cursor.getColumnIndex("DFunctions"));
            String tempDIsJoinDepartment = cursor.getString(cursor.getColumnIndex("DIsJoinDepartment"));
            if(tempDIsJoinDepartment.equalsIgnoreCase("false"))
                department[i].DIsJoinDepartment = false;
            else if(tempDIsJoinDepartment.equalsIgnoreCase("true"))
                department[i].DIsJoinDepartment = true;
        }
        sQLiteDatabase.close();
        return department;
    }

    //查询指定部门号的部门的全属性：
    public Department queryDidDepartment(int Did){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery("select * from Department where Did=?",new String[]{String.valueOf(Did)});
        Department department = new Department();
        if( cursor.moveToNext() == true  ) {
            department.Did = cursor.getInt(cursor.getColumnIndex("Did"));
            department.DName = cursor.getString(cursor.getColumnIndex("DName"));
            department.DFunctions = cursor.getString(cursor.getColumnIndex("DFunctions"));
            String tempDIsJoinDepartment = cursor.getString(cursor.getColumnIndex("DIsJoinDepartment"));
            if(tempDIsJoinDepartment.equalsIgnoreCase("false"))
                department.DIsJoinDepartment = false;
            else if(tempDIsJoinDepartment.equalsIgnoreCase("true"))
                department.DIsJoinDepartment = true;
        }
        sQLiteDatabase.close();
        return department;
    }

    //查询指定部门号的部门是否加入
    public int queryDidDIsJoinDepartment(int Did){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery("select DIsJoinDepartment from Department where Did=?",new String[]{String.valueOf(Did)});
        int DIsJoinDepartment = -1;
        if( cursor.moveToNext() == true  ) {
            String tempDIsJoinDepartment = cursor.getString(cursor.getColumnIndex("DIsJoinDepartment"));
            if(tempDIsJoinDepartment.equalsIgnoreCase("false"))
                DIsJoinDepartment = 0;
            else if(tempDIsJoinDepartment.equalsIgnoreCase("true"))
                DIsJoinDepartment = 1;
        }
        sQLiteDatabase.close();
        return DIsJoinDepartment;
    }

    //更新指定部门号的部门是否加入
    public int updateDidDIsJoinDepartment(int Did, boolean DIsJoinDepartment){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.clear();
        values.put("DIsJoinDepartment", String.valueOf(DIsJoinDepartment));
        int results = sQLiteDatabase.update("Department", values, "Did = ?", new String[]{String.valueOf(Did)}) ;
        sQLiteDatabase.close();
        return results;
    }

    /********对 DepartmentActivities 表的操作 **************/
    //查询人物已加入的部门的所有部门活动全属性
    public DepartmentActivities[] queryJoinedDepartmentActivities(){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery("select * from Department,DepartmentActivities where Department.Did=DepartmentActivities.Did and DIsJoinDepartment=?",new String[]{"true"});
        DepartmentActivities[] departmentActivities = new DepartmentActivities[cursor.getCount()];
        for (int i=0; cursor.moveToNext() == true; i++ ){
            departmentActivities[i] = new DepartmentActivities();
            departmentActivities[i].DAid = cursor.getInt(cursor.getColumnIndex("DAid"));
            departmentActivities[i].DAWeek = cursor.getInt(cursor.getColumnIndex("DAWeek"));
            departmentActivities[i].Did = cursor.getInt(cursor.getColumnIndex("Did"));
            departmentActivities[i].DName = cursor.getString(cursor.getColumnIndex("DName"));
            departmentActivities[i].DAName = cursor.getString(cursor.getColumnIndex("DAName"));
            departmentActivities[i].DABeginTime = cursor.getInt(cursor.getColumnIndex("DABeginTime"));
            departmentActivities[i].DAEndTime = cursor.getInt(cursor.getColumnIndex("DAEndTime"));
            departmentActivities[i].DALocation = cursor.getString(cursor.getColumnIndex("DALocation"));
            departmentActivities[i].DAComprehensiveTest = Double.valueOf(cursor.getString(cursor.getColumnIndex("DAComprehensiveTest")));
            departmentActivities[i].DAEnergy = Integer.valueOf(cursor.getString(cursor.getColumnIndex("DAEnergy")));
            String tempDAIsJoinActivity = cursor.getString(cursor.getColumnIndex("DAIsJoinActivity"));
            if(tempDAIsJoinActivity.equalsIgnoreCase("false"))
                departmentActivities[i].DAIsJoinActivity = false;
            else if(tempDAIsJoinActivity.equalsIgnoreCase("true"))
                departmentActivities[i].DAIsJoinActivity = true;
        }
        sQLiteDatabase.close();
        return departmentActivities;
    }

    //查询指定活动号的部门活动的全属性
    public DepartmentActivities queryDAidDepartmentActivities(int DAid){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery("select * from Department,DepartmentActivities where Department.Did=DepartmentActivities.Did and DAid=?",new String[]{String.valueOf(DAid)});
        DepartmentActivities departmentActivities = new DepartmentActivities();
        if( cursor.moveToNext() == true  ) {
            departmentActivities.DAid = cursor.getInt(cursor.getColumnIndex("DAid"));
            departmentActivities.DAWeek = cursor.getInt(cursor.getColumnIndex("DAWeek"));
            departmentActivities.Did = cursor.getInt(cursor.getColumnIndex("Did"));
            departmentActivities.DName = cursor.getString(cursor.getColumnIndex("DName"));
            departmentActivities.DAName = cursor.getString(cursor.getColumnIndex("DAName"));
            departmentActivities.DABeginTime = cursor.getInt(cursor.getColumnIndex("DABeginTime"));
            departmentActivities.DAEndTime = cursor.getInt(cursor.getColumnIndex("DAEndTime"));
            departmentActivities.DALocation = cursor.getString(cursor.getColumnIndex("DALocation"));
            departmentActivities.DAComprehensiveTest = Double.valueOf(cursor.getString(cursor.getColumnIndex("DAComprehensiveTest")));
            departmentActivities.DAEnergy = Integer.valueOf(cursor.getString(cursor.getColumnIndex("DAEnergy")));
            String tempDAIsJoinActivity = cursor.getString(cursor.getColumnIndex("DAIsJoinActivity"));
            if(tempDAIsJoinActivity.equalsIgnoreCase("false"))
                departmentActivities.DAIsJoinActivity = false;
            else if(tempDAIsJoinActivity.equalsIgnoreCase("true"))
                departmentActivities.DAIsJoinActivity = true;
        }
        sQLiteDatabase.close();
        return departmentActivities;
    }

    //查询指定活动号的部门活动是否参加
    public int queryDAidDAIsJoinActivity(int DAid){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery("select DAIsJoinActivity from DepartmentActivities where DAid=?",new String[]{String.valueOf(DAid)});
        int DAIsJoinActivity = -1;
        if( cursor.moveToNext() == true  ) {
            String tempDAIsJoinActivity = cursor.getString(cursor.getColumnIndex("DAIsJoinActivity"));
            if(tempDAIsJoinActivity.equalsIgnoreCase("false"))
                DAIsJoinActivity = 0;
            else if(tempDAIsJoinActivity.equalsIgnoreCase("true"))
                DAIsJoinActivity = 1;
        }
        sQLiteDatabase.close();
        return DAIsJoinActivity;
    }

    //更新指定活动号的部门活动是否参加
    public int updateDAidDAIsJoinActivity(int DAid, boolean DAIsJoinActivity){
        SQLiteDatabase sQLiteDatabase=database.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.clear();
        values.put("DAIsJoinActivity", String.valueOf(DAIsJoinActivity));
        int results = sQLiteDatabase.update("DepartmentActivities", values, "DAid = ?", new String[]{String.valueOf(DAid)}) ;
        sQLiteDatabase.close();
        return results;
    }




}
