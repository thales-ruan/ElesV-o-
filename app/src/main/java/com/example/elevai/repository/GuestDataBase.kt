package com.example.elevai.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.elevai.contantes.DataBaseConstantes

class GuestDataBase(
    context: Context
) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME = "guesdb"
        private const val VERSION = 1
    }

//todo se der erro pode ter sido nessa concatenação
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE " + DataBaseConstantes.GUEST.TABLE_NAME +" ( "+
                     DataBaseConstantes.GUEST.COLUMNS.ID + " integer primary key autoincrement," +
                    DataBaseConstantes.GUEST.COLUMNS.NAME + "  text," +
                    DataBaseConstantes.GUEST.COLUMNS.PRESENCE + " integer);"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }


}