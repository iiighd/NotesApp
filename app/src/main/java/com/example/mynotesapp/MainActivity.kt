package com.example.mynotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var messageET : EditText
    lateinit var addBTN : Button
    lateinit var editBTN : Button
    private lateinit var rvMain: RecyclerView
    private lateinit var noteStorage: ArrayList<Notes>
    private lateinit var rvAdapter: RVAdapter


    var selectedNotes: Notes? = null

    private val databaseHelper by lazy { DBhelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noteStorage = arrayListOf()
         messageET = findViewById(R.id.messageET)
        addBTN = findViewById(R.id.submitBTN)
        editBTN = findViewById(R.id.updeBTN)
        
        addBTN.setOnClickListener {
            val notes = messageET.text.toString()
            databaseHelper.saveData(notes)
            Toast.makeText(this, " Added successfully! ", Toast.LENGTH_SHORT).show()
            noteStorage = databaseHelper.readData()
            rvAdapter.update(noteStorage)
            messageET.text.clear()
        }

        editBTN.setOnClickListener {
            val updatedData = messageET.text.toString()
            databaseHelper.updateData(Notes(selectedNotes!!.pk, updatedData))
            Toast.makeText(this , "UPDATED ", Toast.LENGTH_SHORT).show()
            databaseHelper.updateData()
        }

//            val isAdded = DBhelper(applicationContext).storeNote(messageET.text.toString())
//            if (isAdded)
//                Toast.makeText(this, "Added Successfuly.", Toast.LENGTH_SHORT).show()
//
//            else
//                Toast.makeText(this , "Sorry Try again please .", Toast.LENGTH_SHORT).show()
//        }

        rvMain = findViewById(R.id.mainRV)
        rvAdapter = RVAdapter(this)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)
    }
    fun uploadText(){
        messageET.setText(selectedNotes!!.data)
    }

}