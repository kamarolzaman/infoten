package com.example.kamarol.infoten_v1.Tools;

import android.content.ContentValues;
import android.content.Context;
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
                        "'STARTTIME' integer PRIMARY KEY AUTOINCREMENT," +
                        "'LENGTH' integer DEFAULT NULL," +
                        "'DAY' integer DEFAULT NULL," +
                        "'NAMELOC' text DEFAULT NULL," +
                        "'SECTION' text DEFAULT NULL," +
                        "'LECTURER' text DEFAULT NULL )"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertSubject(int startTime, int length, int day, String nameloc, String section, String lecturer){
        try {
            System.out.println("INSERT");
            SQLiteDatabase db = this.getWritableDatabase();
            //db.execSQL("DELETE from 'CAR_RAW';");
            //db.execSQL("DELETE from 'SUBJECT';");
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
}
