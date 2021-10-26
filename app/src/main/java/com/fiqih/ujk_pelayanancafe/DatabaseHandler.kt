package com.fiqih.ujk_pelayanancafe

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "ActivityDatabase1s"

        private val TABLE_CONTACTS = "ActivityTable"

        private val KEY_ID = "_id"
        private val KEY_NAME = "name"
        private val KEY_NMRMEJA = "nomeja"
        private val KEY_TIME = "time"
        private val KEY_TANGGAL = "tanggal"
        private val KEY_HARGA = "harga"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_NAME + " TEXT,"
                + KEY_NMRMEJA + " TEXT,"
                + KEY_TANGGAL + " TEXT,"
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_HARGA + " TEXT,"
                + KEY_TIME + " TEXT)")

        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }


    // Methot untuk memasukkan DATA / Record

    fun addActivity(emp: MyActivityModel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, emp.name)
        contentValues.put(KEY_NMRMEJA, emp.nomeja)
        contentValues.put(KEY_TANGGAL, emp.tanggal)
        contentValues.put(KEY_TIME, emp.time)
        contentValues.put(KEY_HARGA, emp.harga)


        // Memasukkan detail karyawan menggunakan kueri sisipkan
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        db.close()
        return success
    }

    // metode untuk membaca catatan
    @SuppressLint("Range")
    fun viewActivity(): ArrayList<MyActivityModel> {
        val empList: ArrayList<MyActivityModel> = ArrayList<MyActivityModel>()
        val selectQuery = "SELECT * FROM ${TABLE_CONTACTS}"

        val db = this.readableDatabase

        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var time: String
        var name: String
        var tanggal: String
        var harga: String
        var nomormeja: String



        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                time = cursor.getString(cursor.getColumnIndex(KEY_TIME))
                name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                tanggal = cursor.getString(cursor.getColumnIndex(KEY_TANGGAL))
                harga = cursor.getString(cursor.getColumnIndex(KEY_HARGA))
                nomormeja = cursor.getString(cursor.getColumnIndex(KEY_NMRMEJA))

                val emp = MyActivityModel(id = id, time = time, name = name, tanggal = tanggal, harga = harga, nomeja = nomormeja)
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        return empList
    }

}