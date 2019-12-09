package com.example.weatherapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.weatherapp.city.City
import java.security.AccessControlContext

private const val DATABASE_NAME = "weather.db"
private const val DATABASE_VERSION= 1

private const val CITY_TABLE_NAME= "city"
private const val CYTY_KEY= "id"
private const val CITY_NAME= "name"

private const val CITY_TABLE_CREATE = """
   CREATE TABLE $CITY_TABLE_NAME (
   $CYTY_KEY INTEGER PRIMARY KEY,
   $CITY_NAME TEXT
)
"""

class Database (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    val TAG= Database::class.java.simpleName
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CITY_TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    fun create_city(city: City): Boolean{

        val values = ContentValues()
        values.put(CITY_NAME, city.name)
        Log.d(TAG,"ville crée : $values")
        val id =writableDatabase.insert(CITY_NAME,null,values)
        city.id =id
        return id>0
    }

}