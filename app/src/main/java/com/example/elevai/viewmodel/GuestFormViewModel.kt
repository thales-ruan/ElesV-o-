package com.example.elevai.viewmodel

import androidx.lifecycle.ViewModel
import com.example.elevai.repository.GuestRepository

class GuestFormViewModel : ViewModel() {

    private val repository = GuestRepository.getInstance()

}