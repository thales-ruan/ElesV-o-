package com.example.elevai.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elevai.databinding.FragmentAllGuestsBinding
import com.example.elevai.view.adapter.GuestAdapter
import com.example.elevai.view.listener.OnGuestListener
import com.example.elevai.viewmodel.AllGuestViewModel


class AllGuestFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AllGuestViewModel
    private val adapter = GuestAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(AllGuestViewModel::class.java)

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)
        binding.recyclerAllGuests.adapter = adapter

        val listener = object : OnGuestListener{
            override fun onClick(id: Int) {
                Toast.makeText(context, "click"+id, Toast.LENGTH_SHORT).show()
            }

            override fun onDelete(id: Int) {
                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
                viewModel.delete(id)
                viewModel.getAll()
            }
        }
        adapter.attachListener(listener)

        viewModel.getAll()

        observes()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observes() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuest(it)
        }
    }

}