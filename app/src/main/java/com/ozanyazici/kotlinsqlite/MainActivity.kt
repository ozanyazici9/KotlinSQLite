package com.ozanyazici.kotlinsqlite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {

            val myDatabase = this.openOrCreateDatabase("Musicians", Context.MODE_PRIVATE,null)

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY, name VARCHAR, age Int)")

           //myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('James',50)")
           //myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('Ozan',23)")
           //myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('Ülkü',19)")

            myDatabase.execSQL("DELETE FROM musicians WHERE name = 'James'")

            //myDatabase.execSQL("UPDATE musicians SET age = 99 WHERE name = 'Ozan'")

            //val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE id = 3", null)

            //val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name LIKE '%s'", null)

            val cursor = myDatabase.rawQuery("SELECT * FROM musicians", null)

            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")
            val idIx = cursor.getColumnIndex("id")

            while (cursor.moveToNext()) {
                println("Name: " + cursor.getString(nameIx))
                println("Age: " + cursor.getInt(ageIx))
                println("Id: " + cursor.getInt(idIx))
            }

            cursor.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    

}