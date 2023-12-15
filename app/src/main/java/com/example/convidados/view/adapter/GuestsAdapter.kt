package com.example.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.RowGuestBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.view.viewholder.GuestViewHolder

class GuestsAdapter: RecyclerView.Adapter<GuestViewHolder>() {

    private var guestList: List<GuestModel> = listOf() //estou inicializando a lista com uma lista vazia
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(item.root)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    fun updatedGuests(list: List<GuestModel>){
        guestList = list

    }
}