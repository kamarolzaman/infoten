package com.example.kamarol.infoten_v1.Tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by musyrif on 13-Dec-17.
 */

public class DBHelperMemory extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = null;

    public DBHelperMemory(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("DELETE from 'SUBJECT';");
        System.out.println("SDSD");
        sqLiteDatabase.execSQL(
                "CREATE TABLE 'SUBJECT_RESULT' (" +
                        "'SUBJECT_CODE' text DEFAULT NULL," +
                        "'RESULT' integer DEFAULT NULL," +
                        "'CREDIT_HOUR' integer DEFAULT NULL," +
                        "'SEMESTER' integer DEFAULT NULL," +
                        "'ACADEMIC_YEAR' text DEFAULT NULL,)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertSubject(String subjectCode, String result, double creditHour, Integer semester, Integer academicYear){
        try {
            System.out.println("INSERT");
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("SUBJECT_CODE", subjectCode);
            contentValues.put("RESULT", result);
            contentValues.put("CREDIT_HOUR", creditHour);
            contentValues.put("SEMESTER", semester);
            contentValues.put("ACADEMIC_YEAR", academicYear);
            db.insert("SUBJECT_RESULT", null, contentValues);
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
