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
import com.example.convidados.databinding.FragmentAllBinding
import com.example.convidados.view.adapter.GuestAdapter
import com.example.convidados.view.listener.GuestListener
import com.example.convidados.viewModel.GuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GuestsViewModel
    private val adapter: GuestAdapter = GuestAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        _binding = FragmentAllBinding.inflate(inflater, container, false)

        //Layout
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        //Adapter
        binding.recyclerAllGuests.adapter = adapter

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
                viewModel.getAll()
            }

        }

        observe()

        adapter.attachListener(listener)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
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
