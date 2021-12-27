package com.example.mynotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var messageET : EditText
    lateinit var addBTN : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         messageET = findViewById(R.id.messageET)
        addBTN = findViewById(R.id.submitBTN)
        addBTN.setOnClickListener {
            val isAdded = DBhelper(applicationContext).storeNote(messageET.text.toString())
            if (isAdded)
                Toast.makeText(this , "Added Successfuly.", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this , "Sorry Try again please .", Toast.LENGTH_SHORT).show()
        }

    }
}