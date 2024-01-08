
package com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.ActivityGuestFormBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.viewModel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: GuestFormViewModel
    private var guestId: Int = 0
    private lateinit var binding: ActivityGuestFormBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()

        observe()

        loadData()//carregando os dados do BD

        binding.radioPresence.isChecked = true
    }

    override fun onClick(v: View) {
        val id = v.id

        if (id == R.id.button_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresence.isChecked

            viewModel.save(guestId, name, presence)
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.load(guestId)
        }
    }

    private fun observe(){

        viewModel.saveGuest.observe(this, Observer {
            if(it){
                Toast.makeText(applicationContext,"Sucesso",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"Falha",Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        viewModel.guest.observe(this, Observer {
            binding.editName.setText(it.name)
            if(it.presence){
                binding.radioPresence.isChecked = true
            }else{
                binding.radioAbsent.isChecked = true
            }
        })
    }

    private fun setListeners() {
        binding.buttonSave.setOnClickListener(this)
    }

}


