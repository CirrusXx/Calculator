package com.example.adam.calculator;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.adam.calculator.DataBase.DataBaseManager;

import java.util.ArrayList;

class DataBaseCalculator extends DataBaseManager {

    public DataBaseCalculator(Context context) {
        super(context);
    }

    public boolean insertData(String Equation)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PATH_NAME,Equation);
        long result = database.insert(TABLE_NAME,null,contentValues);
        if(result==-1) return false;
        return true;
    }

    public ArrayList<String> getData()
    {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor result = database.rawQuery("select * from "+TABLE_NAME,null);
        ArrayList<String> listOfEquations = new ArrayList<>();
        while(result.moveToNext())
        {
            listOfEquations.add(result.getString(1));
        }
        return listOfEquations;
    }

    public void clearDatabase()
    {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(SQL_DELETE_ENTRIES);
        onCreate(database);
    }
}

