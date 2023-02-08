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

    fun update(guest: GuestModel): Boolean {

        return try {
            val db = guestDataBase.writableDatabase
            val presence = if (guest.presence) 1 else 0
            val values = ContentValues()

            values.put(DataBaseConstantes.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstantes.GUEST.COLUMNS.PRESENCE, presence)


            val selection = DataBaseConstantes.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstantes.GUEST.TABLE_NAME, values, selection, args)
            true
        } catch (e: java.lang.Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {

        return try {
            val db = guestDataBase.writableDatabase

            val selection = DataBaseConstantes.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstantes.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: java.lang.Exception) {
            false
        }
    }

    fun getAll(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()

        try {

            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstantes.GUEST.COLUMNS.ID,
                DataBaseConstantes.GUEST.COLUMNS.NAME,
                DataBaseConstantes.GUEST.COLUMNS.PRESENCE
            )

            val cursor = db.query(
                DataBaseConstantes.GUEST.TABLE_NAME, projection, null,
                null, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstantes.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstantes.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstantes.GUEST.COLUMNS.PRESENCE))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()

        } catch (e: java.lang.Exception) {
            return list
        }
        return list

    }

    fun get(id: Int): GuestModel? {
        var guest : GuestModel? = null

        try {

            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstantes.GUEST.COLUMNS.ID,
                DataBaseConstantes.GUEST.COLUMNS.NAME,
                DataBaseConstantes.GUEST.COLUMNS.PRESENCE
            )

            val selection = DataBaseConstantes.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstantes.GUEST.TABLE_NAME, projection, selection,
                args, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstantes.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstantes.GUEST.COLUMNS.PRESENCE))

                    guest = GuestModel(id, name, presence == 1)
                }
            }
            cursor.close()

        } catch (e: java.lang.Exception) {
            return guest
        }
        return guest

    }

    fun getPresent(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()

        try {

            val db = guestDataBase.readableDatabase
            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1 ",null)


            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstantes.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstantes.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstantes.GUEST.COLUMNS.PRESENCE))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()

        } catch (e: java.lang.Exception) {
            return list
        }
        return list

    }

    fun getAbsent(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()

        try {

            val db = guestDataBase.readableDatabase
            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 0 ",null)


            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstantes.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstantes.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstantes.GUEST.COLUMNS.PRESENCE))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()

        } catch (e: java.lang.Exception) {
            return list
        }
        return list

    }

}