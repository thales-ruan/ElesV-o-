package com.example.elevai.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.elevai.model.GuestModel
import com.example.elevai.repository.GuestRepository

class AllGuestViewModel(application: Application) : AndroidViewModel(application) {

    private val  repository = GuestRepository.getInstance(application.applicationContext)

    private val listAllGuests = MutableLiveData<List<GuestModel>>()

    val guests: LiveData<List<GuestModel>> = listAllGuests

    fun getAll(){
       listAllGuests.value = repository.getAll()
    }

    fun getPresent(){
        listAllGuests.value = repository.getPresent()
    }

    fun getAbsent(){
        listAllGuests.value = repository.getAbsent()
    }
    fun delete(id: Int){
        repository.delete(id)
    }


}