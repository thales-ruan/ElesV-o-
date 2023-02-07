package com.example.elevai.repository

import android.content.ContentValues
import android.content.Context
import com.example.elevai.contantes.DataBaseConstantes
import com.example.elevai.model.GuestModel

class GuestRepository private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)

    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase
            val presence = if (guest.presence) 1 else 0
            val values = ContentValues()

            values.put(DataBaseConstantes.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstantes.GUEST.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstantes.GUEST.TABLE_NAME, null, values)
            true
        } catch (e: java.lang.Exception) {
            false
        }
    }

    fun update(guest: GuestModel) : Boolean{

        return  try {
            val db = guestDataBase.writableDatabase
            val presence = if (guest.presence) 1 else 0
            val values = ContentValues()

            values.put(DataBaseConstantes.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstantes.GUEST.COLUMNS.PRESENCE, presence)


            val selection = DataBaseConstantes.GUEST.COLUMNS.ID + "id = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstantes.GUEST.TABLE_NAME, values, selection,args)
            true
        }catch (e:java.lang.Exception){
            false
        }



    }
}