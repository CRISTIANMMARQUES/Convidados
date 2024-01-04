package com.example.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.databinding.FragmentPresentBinding
import com.example.convidados.view.adapter.GuestAdapter
import com.example.convidados.view.listener.OnGuestListener
import com.example.convidados.viewModel.GuestsViewModel
import com.example.convidados.viewModel.PresentViewModel

class PresentFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: GuestsViewModel
    private val adapter = GuestAdapter()

    override fun onCreateView( inflater: LayoutInflater,container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        _binding = FragmentPresentBinding.inflate(inflater, container, false)

        binding.recyclerGuests.layoutManager = LinearLayoutManager(context)

        //Adapter
        binding.recyclerGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                Toast.makeText(context, "Alow, fui clicado", Toast.LENGTH_SHORT).show()
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getAll()
            }

        }

        adapter.attachListener(listener)

        viewModel.getAll()

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe(){
        viewModel.guests.observe(viewLifecycleOwner){
            adapter.updatedGuests(it)
        }
    }
}