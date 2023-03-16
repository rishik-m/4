package com.example.program4;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "userData.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table userDetails(name TEXT primary key, contact TEXT, age TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        db.execSQL("drop Table if exists userDetails");
    }

    public boolean insertData(String name, String contact, String age)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("contact",contact);
        contentValues.put("age",age);
        long result = db.insert("userDetails", null, contentValues);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean updateData(String name, String contact, String age)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact",contact);
        contentValues.put("age",age);
        Cursor cursor = db.rawQuery("Select * from userDetails where name = ?", new String[] {name});
        if(cursor.getCount() > 0)
        {
            long result = db.update("userDetails", contentValues, "name=?", new String[] {name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return false;
        }

    }


    public boolean deleteData(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor cursor = db.rawQuery("Select * from userDetails where name = ?", new String[] {name});
        if(cursor.getCount() > 0)
        {
            long result = db.delete("userDetails", "name=?", new String[] {name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return false;
        }

    }

    public Cursor viewData()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from userDetails", null);
        return cursor;

    }
}
