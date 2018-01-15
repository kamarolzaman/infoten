package com.example.kamarol.infoten_v1.Tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by musyrif on 13-Dec-17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "infoten.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("DELETE from 'SUBJECT';");
        System.out.println("SDSD");
        sqLiteDatabase.execSQL(
                "CREATE TABLE 'SUBJECT' (" +
                        "'ID' integer PRIMARY KEY AUTOINCREMENT," +
                        "'STARTTIME' integer DEFAULT NULL," +
                        "'LENGTH' integer DEFAULT NULL," +
                        "'DAY' integer DEFAULT NULL," +
                        "'NAMELOC' text DEFAULT NULL," +
                        "'SECTION' text DEFAULT NULL," +
                        "'LECTURER' text DEFAULT NULL )"
        );
        sqLiteDatabase.execSQL(
                "CREATE TABLE 'EXAMINATION' (" +
                        "'ID' integer PRIMARY KEY AUTOINCREMENT," +
                        "'code' text DEFAULT NULL," +
                        "'name' text DEFAULT NULL," +
                        "'date' text DEFAULT NULL," +
                        "'section' text DEFAULT NULL," +
                        "'seat' text DEFAULT NULL," +
                        "'loc' text DEFAULT NULL," +
                        "'start' integer DEFAULT NULL," +
                        "'end' integer DEFAULT NULL )"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(
                "DROP TABLE 'EXAMINATION'"
        );
        sqLiteDatabase.execSQL(
                "DROP TABLE 'SUBJECT'"
        );
        sqLiteDatabase.execSQL(
                "CREATE TABLE 'EXAMINATION' (" +
                        "'ID' integer PRIMARY KEY AUTOINCREMENT," +
                        "'code' text DEFAULT NULL," +
                        "'name' text DEFAULT NULL," +
                        "'date' text DEFAULT NULL," +
                        "'section' text DEFAULT NULL," +
                        "'seat' text DEFAULT NULL," +
                        "'loc' text DEFAULT NULL," +
                        "'start' integer DEFAULT NULL," +
                        "'end' integer DEFAULT NULL )"
        );
        sqLiteDatabase.execSQL(
                "CREATE TABLE 'SUBJECT' (" +
                        "'ID' integer PRIMARY KEY AUTOINCREMENT," +
                        "'STARTTIME' integer DEFAULT NULL," +
                        "'LENGTH' integer DEFAULT NULL," +
                        "'DAY' integer DEFAULT NULL," +
                        "'NAMELOC' text DEFAULT NULL," +
                        "'SECTION' text DEFAULT NULL," +
                        "'LECTURER' text DEFAULT NULL )"
        );
        sqLiteDatabase.execSQL(
                "CREATE TABLE 'EXAMINATION' (" +
                        "'ID' integer PRIMARY KEY AUTOINCREMENT," +
                        "'code' text DEFAULT NULL," +
                        "'name' text DEFAULT NULL," +
                        "'date' text DEFAULT NULL," +
                        "'section' text DEFAULT NULL," +
                        "'seat' text DEFAULT NULL," +
                        "'loc' text DEFAULT NULL," +
                        "'start' integer DEFAULT NULL," +
                        "'end' integer DEFAULT NULL )"
        );
    }
    public boolean deleteSubjects(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM SUBJECT");
        return true;
    }
    public boolean deleteExam(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM EXAMINATION");
        return true;
    }
    public boolean insertSubject(int startTime, int length, int day, String nameloc, String section, String lecturer){
        try {
            System.out.println("INSERT");
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("STARTTIME", startTime);
            contentValues.put("LENGTH", length);
            contentValues.put("DAY", day);
            contentValues.put("NAMELOC", nameloc);
            contentValues.put("SECTION", section);
            contentValues.put("LECTURER", lecturer);
            db.insert("SUBJECT", null, contentValues);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public Cursor toArraySubject (){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM SUBJECT",null);
    }
    public Cursor toArrayExamination (){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM EXAMINATION",null);
    }

    public boolean insertExamination(String code, String name, String date, String section, String seat, String loc, int start, int end) {
        try {
            System.out.println("INSERT");
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("code", code);
            contentValues.put("name", name);
            contentValues.put("date", date);
            contentValues.put("section", section);
            contentValues.put("seat", seat);
            contentValues.put("loc", loc);
            contentValues.put("start", start);
            contentValues.put("end", end);
            db.insert("EXAMINATION", null, contentValues);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
