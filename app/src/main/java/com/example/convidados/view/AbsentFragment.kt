package com.example.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devmasterteam.convidados.service.constants.GuestConstants
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.FragmentAbsentBinding
import com.example.convidados.view.adapter.GuestAdapter
import com.example.convidados.view.listener.GuestListener
import com.example.convidados.viewModel.GuestsViewModel

class AbsentFragment : Fragment() {

    private var _binding: FragmentAbsentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GuestsViewModel
    private val adapter = GuestAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        _binding = FragmentAbsentBinding.inflate(inflater, container, false)

        binding.recyclerAbsents.layoutManager = LinearLayoutManager(context)

        binding.recyclerAbsents.adapter = adapter

        val listener = object : GuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()

                bundle.putInt(GuestConstants.GUEST.ID, id)

                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getAbsent()
            }

        }

        observe()

        adapter.attachListener(listener)

        return binding.root
    }

    override fun onResume(){
        super.onResume()
        viewModel.getAbsent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe(){
        viewModel.guestList.observe(viewLifecycleOwner){
            adapter.updatedGuests(it)
        }
    }
}