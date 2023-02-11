package com.example.elevai.repository

import android.content.ContentValues
import android.content.Context
import com.example.elevai.contantes.DataBaseConstantes
import com.example.elevai.model.GuestModel

class GuestRepository(context: Context) {

    private val guestDataBase = GuestDataBase.getDataBase(context).guestDao()


    fun insert(guest: GuestModel): Boolean {
        return guestDataBase.insert(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return guestDataBase.update(guest) > 0
    }

    fun delete(id: Int) {
        val guest = get(id)
        guestDataBase.delete(guest)
    }

    fun get(id: Int): GuestModel {
        return guestDataBase.get(id)

    }

    fun getAll(): List<GuestModel> {
        return guestDataBase.getAll()
    }

    fun getPresent(): List<GuestModel> {
        return guestDataBase.getPresent()

    }

    fun getAbsent(): List<GuestModel> {
        return guestDataBase.getAbsent()
    }


}