package com.example.elevai.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.elevai.model.GuestModel
import com.example.elevai.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    fun insert(guest: GuestModel) {
        repository.insert(guest)
    }
}