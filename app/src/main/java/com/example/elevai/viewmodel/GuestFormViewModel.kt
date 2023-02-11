package com.example.elevai.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.elevai.model.GuestModel
import com.example.elevai.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    private val mSaveGuest = MutableLiveData<String>()
    val saveguest: LiveData<String> = mSaveGuest


    fun save(guest: GuestModel) {
        if (guest.id == 0) {
            if (repository.insert(guest)) {
                mSaveGuest.value = "Inserção com Sucesso"
            } else {
                mSaveGuest.value = "Falha"
            }
        } else {
            if (repository.update(guest)) {
                mSaveGuest.value = "Atualização com Sucesso"
            } else {
                mSaveGuest.value = "Falha"
            }
        }
    }

    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }

}