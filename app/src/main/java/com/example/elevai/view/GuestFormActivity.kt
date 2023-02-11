package com.example.elevai.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.elevai.R
import com.example.elevai.contantes.DataBaseConstantes
import com.example.elevai.databinding.ActivityGuestFormBinding
import com.example.elevai.model.GuestModel
import com.example.elevai.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)

        observes()
        loadData()

    }

    override fun onClick(v: View) {
        if (v.id == R.id.buttonSave) {
            val name = binding.editTextName.text.toString()
            val presence = binding.radioPresente.isChecked

            val model = GuestModel().apply {
                this.id = guestId
                this.name = name
                this.presence = presence
            }

            viewModel.save(model)


        }
    }


    private fun observes() {
        viewModel.guest.observe(this, Observer {
            binding.editTextName.setText(it.name)
            if (it.presence) {
                binding.radioPresente.isChecked = true
            } else {
                binding.radioAusente.isChecked = true
            }
        })

        viewModel.saveguest.observe(this, Observer {
            if (it != "") {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstantes.GUEST.ID)
            viewModel.get(guestId)
        }
    }

}

