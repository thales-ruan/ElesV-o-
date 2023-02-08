package com.example.elevai.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elevai.databinding.AdapterAllGuestBinding
import com.example.elevai.model.GuestModel

class GuestAdapter : RecyclerView.Adapter<GuestAdapter.GuestViewHolder>() {

    private var guestList: List<GuestModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdapterAllGuestBinding.inflate(layoutInflater, parent, false)
        return GuestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(guestList[position])
    }

    override fun getItemCount(): Int {
        return guestList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateGuest(list: List<GuestModel>) {
        guestList = list
        notifyDataSetChanged()
    }

    class GuestViewHolder(val binding: AdapterAllGuestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(guest: GuestModel) {
            binding.textNome.text = guest.name
        }
    }


}