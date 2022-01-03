package com.example.mynotesapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBhelper(context: Context): SQLiteOpenHelper(context, "notes.db", null , 2) {
    private var SQLiteDatabase : SQLiteDatabase = writableDatabase
    var selectedNotes: Notes? = null


    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table details (pk INTEGER PRIMARY KEY AUTOINCREMENT, Name text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS details")
        onCreate(p0)
    }

    fun saveData(name: String){
        val contentValues = ContentValues()
        contentValues.put("Name", name)
        SQLiteDatabase.insert("details", null, contentValues)
    }

    fun readData(): ArrayList<Notes>{
        val notArrayList = arrayListOf<Notes>()

        // Read all data using cursor
        val cursor: Cursor = SQLiteDatabase.rawQuery("SELECT * FROM details", null)

        if(cursor.count < 1){
            println("No Data Found")
        }else{
            while(cursor.moveToNext()){
                val pk = cursor.getInt(0)
                val name = cursor.getString(1)
                notArrayList.add(Notes(pk, name))
            }
        }
        return notArrayList
    }
    fun updateData(note: Notes ){
        val contentValues = ContentValues()
        contentValues.put("Text", note.data )
        SQLiteDatabase.update("details", contentValues , "${note.pk}",null)
    }
    fun deletData(note: Notes){
        SQLiteDatabase.delete("details", "${note.pk}", null)
    }
}