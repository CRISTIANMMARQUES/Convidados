package com.example.convidados.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    private val _saveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = _saveGuest

    private val _guest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = _guest



    fun save(id: Int, name: String, presence: Boolean) {
        val guest = GuestModel(id, name, presence)
        if (id == 0) {
            _saveGuest.value = repository.save(guest)
        } else {
            _saveGuest.value = repository.update(guest)
        }
    }

    fun load(id: Int) {
        _guest.value = repository.get(id)
    }
}