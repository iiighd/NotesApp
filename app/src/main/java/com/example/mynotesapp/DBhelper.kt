package com.example.mynotesapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBhelper(context: Context): SQLiteOpenHelper(context, "notes.db", null , 1) {
    private var SQLiteDatabase : SQLiteDatabase = writableDatabase

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table Notes (Content text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
        //to using this we need ton upgrade vergain
    }

    fun storeNote (content: String) : Boolean {
        val contentValues = ContentValues()
        contentValues.put("Content", content)
        var status = SQLiteDatabase.insert("notes", null , contentValues)
        return status != -1L

    }
}