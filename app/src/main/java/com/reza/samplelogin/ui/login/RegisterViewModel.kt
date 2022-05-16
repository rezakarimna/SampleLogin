package com.reza.appmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reza.appmovies.data.models.ResponseRegister
import com.reza.samplelogin.data.models.BodyRegister
import com.reza.samplelogin.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerRepository: LoginRepository) :
    ViewModel() {

    private val _registerUser = MutableLiveData<ResponseRegister>()
    val registerUser: LiveData<ResponseRegister>
        get() = _registerUser

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun sendRegisterUser(bodyRegister: BodyRegister) = viewModelScope.launch {
        _loading.postValue(true)
        val response = registerRepository.login(bodyRegister)
        if (response.isSuccessful) {
            _registerUser.postValue(response.body())
        }
        _loading.postValue(false)

    }
}