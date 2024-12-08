package com.angelatest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelatest.data.repository.home.medicineRepository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private val _problemsList = MutableStateFlow<List<Map<String, String>>>(emptyList())
    val problemsList: StateFlow<List<Map<String, String>>> = _problemsList


    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    fun fetchDrugs() {
        viewModelScope.launch {
            try {
                val drugs = repository.getProblems()
                _problemsList.value = drugs
            } catch (e: Exception) {
                _error.value = "No data found"
            }
        }
    }

    fun setUserName(name: String) {
        _username.value = name
    }
}