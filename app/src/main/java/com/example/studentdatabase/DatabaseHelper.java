package com.example.studentdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "school.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE = "students";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_COURSE = "course";
    public static final String COL_YEAR = "year";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT NOT NULL, "
                + COL_COURSE + " TEXT NOT NULL, "
                + COL_YEAR + " INTEGER NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    // INSERT: returns the new row id, or -1 on failure
    public long insertStudent(String name, String course, int year) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_COURSE, course);
        values.put(COL_YEAR, year);
        return db.insert(TABLE, null, values);
    }

    // UPDATE: returns the number of rows changed
    public int updateStudent(int id, String name, String course, int year) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_COURSE, course);
        values.put(COL_YEAR, year);
        return db.update(TABLE, values, COL_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    // DELETE: returns the number of rows removed
    public int deleteStudent(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE, COL_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    // SELECT: returns every student
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE, null, null, null, null, null,
                COL_ID + " ASC");
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME));
                String course = cursor.getString(cursor.getColumnIndexOrThrow(COL_COURSE));
                int year = cursor.getInt(cursor.getColumnIndexOrThrow(COL_YEAR));
                list.add(new Student(id, name, course, year));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
