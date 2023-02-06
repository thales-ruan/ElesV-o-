package com.example.elevai.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.elevai.R
import com.example.elevai.databinding.ActivityGuestFormBinding
import com.example.elevai.model.GuestModel
import com.example.elevai.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityGuestFormBinding
    private lateinit var viewModel : ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if(v.id == R.id.buttonSave){

         //   GuestModel(10,"x",false)
        }
    }
}