package com.example.absensi_rizky

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AbsensiViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AbsensiRepository
    val allAbsensi: LiveData<List<Absensi>>

    init {
        val absensiDao = AbsensiDatabase.getDatabase(application).absensiDao()
        repository = AbsensiRepository(absensiDao)
        allAbsensi = repository.allAbsensi
    }

    fun insert(absensi: Absensi) = viewModelScope.launch {
        repository.insert(absensi)
    }

    fun update(absensi: Absensi) = viewModelScope.launch {
        repository.update(absensi)
    }

    fun delete(absensi: Absensi) = viewModelScope.launch {
        repository.delete(absensi)
    }
}
