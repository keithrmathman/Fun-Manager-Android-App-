package com.example.keithr.funmanager;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Keith r on 12/12/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_NAME = "User";

    private static final String TABLE_USER_INFORMATION = "UserInformation";
    private static final String TABLE_USER_MOMENTS = "UserFunMoments";
    private static final String TABLE_MOMENTS_USER_ALLOCATION = "UserMomentAllocation";

   //all User information variables
    private static String User_ID = "UserID";
    private static String UserName = "Username";
    private static String User_pw = "Password";
    private static String User_FirstName = "FirstName";
    private static String User_LastName = "LastName";
    private static String User_Age = "Age";
    private static String UserFavFood = "Fav_food";
    private static String UserFavColor = "Fav_Color";
    private static String UserBestMoment = "Best_moment";

    //all User Fun Moments Data
    private static String MomentID = "MomentID";
    private static String MomentTitle = "MomentTitle";
    private static String MomentDescription = "MomentDescr";
    private static String MomSpecialPersons = "MomentSpecialPersons";
    private static String MomentDate = "DateOfMoment";
    private static String MomentParticipants = "People Who Participated";

    //all User Moment Allocation Data
    private static String Num_of_Moments = "NumberofMoments";
    //UserID will be used to placehold generated User IDs
    private int UserID;

    public DBHandler(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_INFO_TABLE = "CREATE TABLE " + TABLE_USER_INFORMATION + "("
                + User_ID + " Integer Primary key  Not Null," +
                User_FirstName + " VarChar(50) Not Null," +
                User_LastName + " VarChar(50),"  +
                User_Age + " Integer," +
                UserName + " VarChar(25) Not Null," +
                User_pw +  " VarChar(14) Not Null," +
                UserFavFood + " Varchar(50)," +
                UserBestMoment + " Varchar(600));";

        String CREATE_USER_MOMENTS_TABLE = "CREATE TABLE " + TABLE_USER_MOMENTS + "("
                + MomentID + " Integer Primary key  Not Null," +
                MomentTitle + " VarChar(130) Not Null," +
                MomentDescription + " VarChar(1000),"  +
                MomSpecialPersons + " VarChar(500)," +
               MomentDate + " VarChar(30) ," +
                MomentParticipants +  " VarChar(500)); " ;

        String CREATE_USER_MOMENT_ALLOCATION_TABLE = "CREATE TABLE " + TABLE_MOMENTS_USER_ALLOCATION + "("
                + MomentID + " Integer  Not Null," +
                User_ID + " Integer Primary Key Not Null," +
                Num_of_Moments + " Integer," +
                " Foreign Key (" + MomentID + ") References "
                + TABLE_USER_MOMENTS + "("+MomentID + ")," +
                " Foreign Key (" + User_ID +
                ") References " + TABLE_USER_INFORMATION + "("+User_ID + "));";


        db.execSQL(CREATE_USER_INFO_TABLE);
        db.execSQL(CREATE_USER_MOMENTS_TABLE);
        db.execSQL(CREATE_USER_MOMENT_ALLOCATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROPS TABLE IF EXISTS " + TABLE_USER_INFORMATION);
        db.execSQL("DROPS TABLE IF EXISTS " + TABLE_USER_MOMENTS);
        db.execSQL("DROPS TABLE IF EXISTS " + TABLE_MOMENTS_USER_ALLOCATION);
        onCreate(db);
    }

    public void addNewUser( SignUp user){
         SQLiteDatabase db = this.getWritableDatabase();

        ContentValues content = new ContentValues();

        while(!checkForDuplicateID(UserID))
           checkForDuplicateID(UserID);

        content.put(User_ID, UserID);
        content.put(User_FirstName,user.getUserFirstName() );
        content.put(User_LastName, user.getUserLastName());
        content.put(User_Age, user.getAge());
        content.put(UserFavFood,user.getFavoriteFood() );
        content.put(UserName,user.getUserName() );
        content.put(User_pw,user.getFavoriteFood() );
        content.put(UserFavColor, user.getFavoritecolor());
        content.put(UserBestMoment, user.getBestMoment());
        //insert Row into DataBase
        db.insert(TABLE_USER_INFORMATION, null, content);
        db.close();
    }

    public void DeleteUser(DeleteUser userdel){

        SQLiteDatabase db = this.getWritableDatabase();

        //Execute sql query to remove from database
        //NOTE: When removing by String in SQL, value must be enclosed with ''
     //   db.execSQL("DELETE * FROM " + TABLE_USER_INFORMATION + " WHERE " + UserID + "= '" + +"'");

        //Close the database
        db.close();
    }

    public boolean LoginUser(login Login){

        boolean LoggedIn = false;

        SQLiteDatabase db = getReadableDatabase();

        String username = new String();
        String password = new String();

        Cursor cursor = db.query(TABLE_USER_INFORMATION, new String[] { User_ID,
                        UserName, User_pw }, UserName + "=?" + User_pw + "=?",
                new String[] { String.valueOf(Login.Username), String.valueOf(Login.Password)}, null, null, null, null);
        if (cursor != null)
            if(cursor.moveToFirst()) {
                LoggedIn = true;
            }
        return LoggedIn;
    }

    //Generates random UserID for new User
    private int GenerateUserID(){
        //range of UserIDs
        int min = 0000001;
        int max = 1111111;
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;

    }

    private boolean checkForDuplicateID(int ID){


        SQLiteDatabase dataBase = getReadableDatabase();
        ID = GenerateUserID();
        Cursor c = dataBase.rawQuery("SELECT 1 FROM "+TABLE_USER_INFORMATION+" WHERE "+User_ID+"=?", new String[] {String.valueOf(ID)});
        boolean exists = c.moveToFirst();
        c.close();
        return exists;

    }
}
