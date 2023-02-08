package com.example.elevai.view.adapter

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.elevai.databinding.AdapterAllGuestBinding
import com.example.elevai.model.GuestModel
import com.example.elevai.view.listener.OnGuestListener

class GuestAdapter : RecyclerView.Adapter<GuestAdapter.GuestViewHolder>() {

    private var guestList: List<GuestModel> = listOf()
    private lateinit var listener: OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdapterAllGuestBinding.inflate(layoutInflater, parent, false)
        return GuestViewHolder(binding, listener)
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

    fun attachListener(guestListener: OnGuestListener) {
        listener = guestListener
    }

    class GuestViewHolder(
        private val binding: AdapterAllGuestBinding,
        private val listener: OnGuestListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(guest: GuestModel) {
            binding.textNome.text = guest.name


            binding.textNome.setOnClickListener {
                listener.onClick(guest.id)
            }

            binding.imageDelete.setOnClickListener {

                AlertDialog.Builder(itemView.context)
                    .setTitle("Remover Convidado")
                    .setMessage("Tem certeza que deseja remover")
                    .setPositiveButton(
                        "Sim"
                    ) { dialog, which ->
                        listener.onDelete(guest.id)
                    }
                    .setNegativeButton("Não", null)
                    .create()
                    .show()
            }

        }
    }


}