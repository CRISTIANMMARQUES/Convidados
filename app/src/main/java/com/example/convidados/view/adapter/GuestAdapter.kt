package com.example.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.RowGuestBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.view.listener.GuestListener
import com.example.convidados.view.viewholder.GuestViewHolder

class GuestAdapter: RecyclerView.Adapter<GuestViewHolder>() {

    private var guestList: List<GuestModel> = listOf() //estou inicializando com uma lista vazia
    private lateinit var listener: GuestListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        //countCreate++
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(item, listener)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {

        holder.bind(guestList[position])
    }

    override fun getItemCount(): Int {//tamanho da lista
        return guestList.count()
    }

    fun updatedGuests(list: List<GuestModel>){
        guestList = list
        notifyDataSetChanged()
    }

    fun attachListener(guestListener: GuestListener){
        listener = guestListener
    }
}